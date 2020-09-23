import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 * 客户端开辟的去消息的线程
 */
public class ClientHandler extends Thread{

	private final static int phonePort=5555;
	private final static int phonePort1=5556;

	Socket socket;
	
	//登录窗体
	LoginFrame loginFrame;
	
	//聊天主窗体
	ChatFrame chatFrame;
	
	public ClientHandler(Socket socket , LoginFrame loginFrame) {
		this.socket = socket;
		this.loginFrame = loginFrame;
	}
	
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
						loginResult(tfi);
					}else if (tfi.getStatusEnum() == ChatStatus.REGESTER){
						System.out.println("注册成功!");
					} else if(tfi.getStatusEnum() == ChatStatus.CHAT){
						//这是聊天消息
						chatResult(tfi);
					}else if(tfi.getStatusEnum()==ChatStatus.PHONE){
						//语音通话
						phoneResult(tfi);
					}else if(tfi.getStatusEnum()==ChatStatus.PHONEBACK){
						phoneBackResult(tfi);
					}
					else if(tfi.getStatusEnum() == ChatStatus.NOTICE){
						noticeResult(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.SEND_FILE){
						//客户端接收文件
						fileAcceptResult(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.ULIST){
						//刷新当前在线用户列表
						onlineUsersResult(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.DD){
						//抖动相应处理
						DDResult(tfi);
					}else if(tfi.getStatusEnum()==ChatStatus.REFLRSH){
						//刷新头像
						onlineUsersResult(tfi);
					}else if(tfi.getStatusEnum()==ChatStatus.BAN){
						//封禁账号结果
						TurnOff();
					}
				} else {
					
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	//关闭封禁账号的窗口
	private void TurnOff() {
		chatFrame.dispose();
		chatFrame.private_chat_window.dispose();
	}


	/**
	 * 接收文件
	 * @param tfi
	 * @throws IOException 
	 */
	private void fileAcceptResult(TransferInfo tfi) throws IOException {
		//接收文件的时候可以指定具体的目录
		JFileChooser jfc = new JFileChooser();
		//只能选择文件夹
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//打开文件选择窗体
		int state = jfc.showDialog(new JLabel(), "保存文件");
		
		if(state == JFileChooser.CANCEL_OPTION) {
			System.out.println("用户取消下载");
			//取消
			return;
		}
		//获取文件
		File directory = jfc.getSelectedFile();
		String filePath = directory.getAbsolutePath();
		File saveFile = new File(filePath , tfi.getFileName());
		FileOutputStream fos = new FileOutputStream(saveFile);
		fos.write(tfi.getFileByte());
		fos.flush();
		fos.close();
		System.out.println("用户下载完成");
		
		TransferInfo tfi2 = new TransferInfo();
		tfi2.setSender(tfi.getReciver());
		tfi2.setReciver(tfi.getSender());
		tfi2.setNotice(">>> " + tfi.getReciver() + "已经成功接收到您的文件，文件名：" + tfi.getFileName());
		tfi2.setStatusEnum(ChatStatus.NOTICE);
		IOStream.writeMessage(socket, tfi2);
	}
	
	/**
	 * 接收从服务器发送过来的抖动信息
	 * @param tfi
	 */
	private void DDResult(TransferInfo tfi) {
		DouDong dd = new DouDong(chatFrame);
		dd.start();
	}
	
	/**
	 * 登录结果的处理
	 * @param tfi
	 */
	public void loginResult(TransferInfo tfi) {
		if(tfi.getLoginSucceessFlag()) {
			
			//根据实体类取出用户名
			String account = tfi.getAccount();
			
			//登录成功，打开主界面
			chatFrame = new ChatFrame(account , socket);
			loginFrame.dispose();//关闭登录窗体
		}else {
			//登录失败
			System.out.println("客户端接收到登录失败");
			
		}
	}
	
	/**
	 * 聊天消息处理
	 * @param tfi
	 */
	public void chatResult(TransferInfo tfi) {
		//发送人
		String sender = tfi.getSender();
		//接收人
		String reciver = tfi.getReciver();
		//文字解析类进行解析处理
		List<FontStyle> contents = tfi.getContent();
		if(reciver != null && "All".equals(reciver)) {
			reciver = "所有人";//All转换成所有人
			FontSupport.fontDecode(chatFrame.acceptPane, contents, sender, reciver);
		}
		else
		{
			FontSupport.fontDecode(chatFrame.private_chat_window.out, contents, sender, reciver);
		}
	}

	//语音通话
	private void phoneResult(TransferInfo tfi) {
		System.out.println("收到通话请求");
//		实现语音通话
		try {
			//接收通话的客户端方连接请求通话方搭建的临时服务器
			Socket socket1=new Socket(tfi.getPhoneIP(),phonePort);
			Playback playback=new Playback(socket1);
			playback.start();

			tfi.setStatusEnum(ChatStatus.SENDPHONE);
			IOStream.writeMessage(socket,tfi);

			ServerSocket serverSocket=new ServerSocket(phonePort1);
			Socket socket2=serverSocket.accept();
			Capture capture=new Capture(socket2);
			capture.start();


//			tfi.setReceiveSocket(socket1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void phoneBackResult(TransferInfo tfi) {
		try {
			Socket socket=new Socket(tfi.getPhoneIP(),phonePort1);
			Playback playback=new Playback(socket);
			playback.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 系统消息提示
	 * @param tfi
	 */
	public void noticeResult(TransferInfo tfi) {
		//往公屏上面投射系统消息
		FontSupport.contentAppend(chatFrame.acceptPane, "\n" + tfi.getNotice());
	}
	
	/**
	 * 刷新当前界面的用户列表
	 * @param tfi
	 */
	public void onlineUsersResult(TransferInfo tfi) {
		String[] userOnlineArray = tfi.getUserOnlineArray();
		//你想展示什么，就添加什么
		ImageListModel model = new ImageListModel();
		for (String account : userOnlineArray) {
			User user = new UserDaoImpl().loadData(account);
			user.setUsername(user.getUsername() + "昵称");
			user.setMotto(user.getMotto());

			user.setPicture("src/image/uicon/" + user.getPicture() + ".png");
			model.addElement(user);
		}
		//JList的模型，给我们存放数据的
		chatFrame.lstUser.setModel(model);
		//提供给我们自定义想要的皮肤或者样式
		chatFrame.lstUser.setCellRenderer(new ImageCellRenderer());
	}
	
}
