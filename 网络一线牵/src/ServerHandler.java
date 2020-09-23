import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JList;
import javax.swing.JTextPane;

/**
 * 服务器端开辟一个线程，来处理一直读消息
 */
public class ServerHandler extends Thread {

	Socket socket;

	UserDaoImpl database = new UserDaoImpl();
	
	ServerFrame serverFrame;
	
	public ServerHandler(Socket socket , ServerFrame serverFrame) {
		this.socket = socket;
		this.serverFrame = serverFrame;
	}
	
	static List<String> onlineUsers = new ArrayList<>();
	static List<Socket> onlineSockets = new ArrayList<>();
	
	//每个人持有的连接在服务器端都是存在的，需要根据接收人来发送消息
	
	@Override
	public void run() {
		
		//默认重复拿
		while(true) {
			try {
				//模拟一直拿消息，产生阻塞
				Object obj = IOStream.readMessage(socket);
				if(obj instanceof TransferInfo) {
					TransferInfo tfi = (TransferInfo)obj;
					if(tfi.getStatusEnum() == ChatStatus.LOGIN) {
						//这是登录
						loginHandler(tfi);
						
					}else if(tfi.getStatusEnum()==ChatStatus.REGESTER) {
						//注册
						registerHandler(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.CHAT){
						//这是聊天消息
						chatHandler(tfi);
						
					}else if(tfi.getStatusEnum()==ChatStatus.PHONE){
						//语音通话
						phoneHandler(tfi);
					}else if(tfi.getStatusEnum()==ChatStatus.SENDPHONE){
						//接收者的消息返回
						phoneAwsHandler(tfi);
					} else if(tfi.getStatusEnum()==ChatStatus.TIP_OFF){
						//举报消息
						tip_off(tfi);
					}else if(tfi.getStatusEnum()==ChatStatus.BAN){
						//封禁账号
						TurnOff(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.DD){
						//这是抖动的消息
						doudong(tfi);
						
					} else if(tfi.getStatusEnum() == ChatStatus.SEND_FILE){
						//文件到达服务器之后：先存储，再转发
						saveFileByServer(tfi);
						sendFile(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.NOTICE){
						notice(tfi);
					}else if(tfi.getStatusEnum()==ChatStatus.REFLRSH){
						//刷新更改头像后的用户列表
						refresh(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.QUIT){
						//退出处理
						loginOut(tfi);
						//休眠1秒后
						Thread.sleep(1000);
						//关闭当前socket
						socket.close();
						//关闭当前线程
						this.interrupt();
						//跳出循环
						break;
					}
				}
				Thread.sleep(1000);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}



	private void notice(TransferInfo tfi)  {
		String reciver = tfi.getReciver();
		//取出接收人的Socket
		Socket socket2 = ChatServer.userSocketMap.get(reciver);
		//如果当前人在线的话
		if(socket2 != null && !socket2.isClosed()) {
			IOStream.writeMessage(socket2, tfi);
		}
	}
	/**
	 * 将文件保存至服务器
	 * @param tfi
	 * @throws IOException
	 */
	private void saveFileByServer(TransferInfo tfi) throws IOException {
		String fileName = tfi.getFileName();
		File file = new File("D:/" + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(tfi.getFileByte());
		fos.flush();
		fos.close();
	}
	
	/**
	 * 发送文件
	 * @param tfi
	 */
	private void sendFile(TransferInfo tfi) {
		String reciver = tfi.getReciver();
		//取出接收人的Socket
		Socket socket2 = ChatServer.userSocketMap.get(reciver);
		//如果当前人在线的话
		if(socket2 != null && !socket2.isClosed()) {
			IOStream.writeMessage(socket2, tfi);
		}else {
			System.out.println("文件接收人：" +reciver +"不在线");
		}
	}
	
	/**
	 * 用户退出处理，清理在线人数，刷新用户列表，告诉所有人，你已经离开
	 * @param tfi
	 */
	public void loginOut(TransferInfo tfi) {
		String userName = tfi.getAccount();
		//将该用户从用户集合移除
		Iterator<String> userIter = onlineUsers.iterator();
		while(userIter.hasNext()) {
			if(userIter.next().equals(userName)) {
				userIter.remove();
			}
		}
		
		//将该用户从socket集合移除
		Iterator<Socket> socketIter = onlineSockets.iterator();
		while(socketIter.hasNext()) {
			Socket next = socketIter.next();
			if(socket == next) {
				socketIter.remove();
			}
		}
		
		//将user与Socket的关系从Map中移除
		ChatServer.userSocketMap.remove(userName);
		
		//刷新服务器面板的用户列表
		flushOnlineUserList();
		
		//给所有在线的用户发送下线消息
		tfi.setStatusEnum(ChatStatus.NOTICE);
		sendAll(tfi);
		
		//告诉其他人刷新用户列表
		tfi.setUserOnlineArray(onlineUsers.toArray(new String [onlineUsers.size()]));
		tfi.setStatusEnum(ChatStatus.ULIST);
		sendAll(tfi);
	}
		
	/**
	 * 发送抖动消息到客户端
	 * @param tfi
	 */
	private void doudong(TransferInfo tfi) {
		//转发给其他用户
		String reciver = tfi.getReciver();
		if("All".equals(reciver)) {
			//发送给所有人
			sendAll(tfi);
			//记录日志
			log(tfi.getSender() + "给所有人发抖动");
		} else {
			//私聊
			send(tfi);
		}
	}
	
	/**
	 * 处理客户端聊天请求
	 * @param tfi
	 */
	private void chatHandler(TransferInfo tfi) {
		//转发给其他用户
		String reciver = tfi.getReciver();
		if("All".equals(reciver)) {
			//发送给所有人
			sendAll(tfi);
			List<FontStyle> contents = tfi.getContent();
			//记录日志
			FontSupport.fontDecode(serverFrame.serverInfoPanel.txtLog, contents, tfi.getSender(), "所有人");
			MP3Player mp3 = new MP3Player("src/music/ding.mp3");
			mp3.play();
		} else {
			//私聊
			List<FontStyle> contents = tfi.getContent();
			send(tfi);
			FontSupport.fontDecode(serverFrame.serverInfoPanel.txtLog, contents, tfi.getSender(), reciver);
			MP3Player mp3 = new MP3Player("src/music/ding.mp3");
			mp3.play();
		}
	}

	/**
	 * @param tfi
	 */
	private void phoneHandler(TransferInfo tfi){
		//发送给私聊的接收者，快捷键直接决定了你学Java的速度
		String sender = tfi.getSender();
		String reciver = tfi.getReciver();
		Socket socket1 = ChatServer.userSocketMap.get(sender);
		Socket socket2 = ChatServer.userSocketMap.get(reciver);
		tfi.setPhoneIP(socket1.getLocalAddress().getHostAddress());
		//实现语音通话
		//接收者
		tfi.setStatusEnum(ChatStatus.PHONE);
		IOStream.writeMessage(socket2 , tfi);
	}

	private void phoneAwsHandler(TransferInfo tfi) {
		//接收者返回的消息处理
		String sender = tfi.getSender();
		String reciver = tfi.getReciver();
		Socket socket1 = ChatServer.userSocketMap.get(sender);
		Socket socket2 = ChatServer.userSocketMap.get(reciver);
		tfi.setPhoneIP(socket2.getLocalAddress().getHostAddress());
		//发送者
		tfi.setStatusEnum(ChatStatus.PHONEBACK);
		IOStream.writeMessage(socket1 , tfi);
	}

	//注册
	private void registerHandler(TransferInfo tfi) {
		System.out.println("正在注册...");
		User user = new User();
		user.setAccount(tfi.getAccount());
		user.setPassword(tfi.getPassword());
		user.setUsername(tfi.getUserName());
		user.setAge(tfi.getAge());
		user.setSex(tfi.getSex());
		user.setPicture("1");//设置默认图片
		database.regist(user);
		System.out.println("注册成功!");
	}
	
	/**
	 * 处理客户端的登录请求
	 * @param tfi
	 */
	private void loginHandler(TransferInfo tfi) {
		boolean flag = checkUserLogin(tfi);
		tfi.setLoginSucceessFlag(false);
		if(flag) {
			//返回登录成功给客户端
			tfi.setLoginSucceessFlag(true);
			tfi.setStatusEnum(ChatStatus.LOGIN);
			//从数据库中将用户数据导入
			User user = database.loadData(tfi.getAccount());

			IOStream.writeMessage(socket , tfi);
			String account = tfi.getAccount();

			//统计在线人数
			onlineUsers.add(account);
			onlineSockets.add(socket);
			
			//在线用户和管道的对应关系
			ChatServer.userSocketMap.put(account , socket);
			
			//发系统消息给客户端，该用户已上线
			tfi = new TransferInfo();
			tfi.setStatusEnum(ChatStatus.NOTICE);
			String notice = "\""+ user.getUsername()+"\"" +" 上线啦";
			tfi.setNotice(notice);
			sendAll(tfi);
			
			//准备最新用户列表给当前客户端
			tfi = new TransferInfo();
			tfi.setUserOnlineArray(onlineUsers.toArray(new String [onlineUsers.size()]));
			tfi.setStatusEnum(ChatStatus.ULIST);
			sendAll(tfi);
			
			//刷新在线用户列表
			flushOnlineUserList();
			
			//记录日志
			log(notice);
		}else {
			//返回登录失败给客户端
			IOStream.writeMessage(socket , tfi);
			//记录日志
			log(tfi.getAccount() + "登录失败");
		}
	}

	private void refresh(TransferInfo tfi) {
			//准备最新用户列表给当前客户端
			tfi.setUserOnlineArray(onlineUsers.toArray(new String [onlineUsers.size()]));
			tfi.setStatusEnum(ChatStatus.ULIST);
			sendAll(tfi);
	}
	
	/**
	 * 记录日志的方法
	 * @param log
	 */
	private void log(String log) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		JTextPane txtLog = serverFrame.serverInfoPanel.txtLog;
		String oldLog = txtLog.getText();
		txtLog.setText(oldLog + "\n" + dateStr + " " + log);
	}
	/**
	 * 刷新服务端用户列表
	 */
	public void flushOnlineUserList() {
		JList lstUser = serverFrame.onlineUserPanel.lstUser;
		
		String[] userArray = onlineUsers.toArray(new String [onlineUsers.size()]);
		
		lstUser.setListData(userArray);
		serverFrame.serverInfoPanel.txtNumber.setText(userArray.length + "");
	}
	
	/**
	 * 发送消息给所有人
	 * @param tfi
	 */
	public void sendAll(TransferInfo tfi) {
		for (int i = 0; i < onlineSockets.size(); i++) {
			Socket tempSocket = onlineSockets.get(i);
			IOStream.writeMessage(tempSocket , tfi);
		}
	}

	public void send(TransferInfo tfi) {
		if(tfi.getStatusEnum() == ChatStatus.BAN)
		{
			String reciver = tfi.getReciver();
			Socket socket1 = ChatServer.userSocketMap.get(reciver);
			IOStream.writeMessage(socket1 , tfi);
		}
		else
		{
			//发送给私聊的接收者，快捷键直接决定了你学Java的速度
			String reciver = tfi.getReciver();
			String sender = tfi.getSender();
			//根据reivcer拿到Socket管道
			//通过用户名为键，管道为值取做map
			Socket socket1 = ChatServer.userSocketMap.get(reciver);
			IOStream.writeMessage(socket1 , tfi);

			Socket socket2 = ChatServer.userSocketMap.get(sender);
			IOStream.writeMessage(socket2 , tfi);
		}

	}
	
	/**
	 * 登录功能
	 * @param tfi
	 * @return
	 */
	public boolean checkUserLogin(TransferInfo tfi) {
		if(database.login(tfi.getAccount(),tfi.getPassword())){
			System.out.println("成功登陆！");
			return true;
		}
		else {
			System.out.println("登陆失败！");
			return false;
		}
	}

	/**
	 * 举报功能
	 */
	public void tip_off(TransferInfo tfi){
		//获取举报人和被举报人
		String sender = tfi.getSender();
		String reciver = tfi.getReciver();
		//获取举报的原因
		String reason = tfi.getTip_offInformation();
		JTextPane tip_offText = serverFrame.tip_offPanel.Tip_offText;
		String oldText = tip_offText.getText();
		if(oldText != " ")
		{
			tip_offText.setText(oldText + "\n\n收到" + sender + "对" + reciver + "的举报\n" + reason);
		}
		else
		{
			tip_offText.setText("收到" + sender + "对" + reciver + "的举报\n" + reason);
		}
	}

	/**
	 * 关闭被封号者的窗口
	 */
	public void TurnOff(TransferInfo tfi)
	{
		send(tfi);
	}
}
