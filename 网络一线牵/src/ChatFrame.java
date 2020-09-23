import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.List;
import javax.swing.*;

/**
 * 聊天主界面
 */
public class ChatFrame extends JFrame {
	public static final Integer FRAME_WIDTH = 730;
	public static final Integer FRAME_HEIGHT = 730;
	JComboBox reciverBox_ChatFrame;
	public JList lstUser;
	String account;
	Socket socket;
	ChatFrame chatFrame;
	static ImageIcon text_image = new ImageIcon();
	public static JLabel text_background = new JLabel(text_image);
	JComboBox cb;
	public JTextPane acceptPane;
	//JButton1是本账号的头像
	public static ImageIcon headPicture;
	public static JButton button1;
	public Private_chat_window private_chat_window;

	public ChatFrame(String account, Socket socket)
	{
		//加载用户头像
		User user=new UserDaoImpl().loadData(account);
		headPicture = new ImageIcon("./head_portrait/head"+user.getPicture()+"(2).png");
		button1 = new JButton(headPicture);


		this.socket = socket;
		this.account = account;
		//创建一个Label用于存放图片，作为背景
		chatFrame=this;
		JLabel jl;
		ImageIcon image;
		image = new ImageIcon("./chatting_background\\background3.jpg");
		jl = new JLabel(image);
		jl.setBounds(0, 0, 730, 730);

		setTitle("聊天主窗口");
		//设置窗口大小不可变化
		setResizable(false);
		setLocation(400, 60);
		setLayout(null);
		setSize(730, 730);

		ImageIcon image2 = new ImageIcon("./wordart\\word2.png");
		JLabel friendList = new JLabel(image2);
		friendList.setBounds(33, 60, 100, 30);

		//创建userList
		JPanel pnlUser = new JPanel();
		pnlUser.setLayout(null);
		pnlUser.setBackground(new Color(52, 130, 203));
		pnlUser.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		pnlUser.setBounds(10, 90, 160, 450);
		pnlUser.setOpaque(false);

		//用户列表
		lstUser = new JList();
		lstUser.setFont(new Font("宋体", 0, 16));
		lstUser.setVisibleRowCount(17);
		lstUser.setFixedCellWidth(180);
		lstUser.setFixedCellHeight(40);
		lstUser.setOpaque(false);

		JPopupMenu popupMenu=new JPopupMenu();
		JMenuItem privateChat=new JMenuItem("私聊");
		popupMenu.add(privateChat);
		privateChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object reciverObj=lstUser.getSelectedValue();
				private_chat_window=new Private_chat_window(account,socket);
				if(reciverObj instanceof User)
				{
					User user = (User)reciverObj;
					String reciver=user.getAccount();

					Private_chat_window.reciverBox.removeAllItems();
					Private_chat_window.reciverBox.addItem("All");
					Private_chat_window.reciverBox.addItem(reciver);
					Private_chat_window.reciverBox.setSelectedItem(reciver);
				}
			}
		});

		//添加点击事件
		lstUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.isMetaDown())
				{
					if(lstUser.getSelectedIndex()>=0)
					{
						popupMenu.show(lstUser,e.getX(),e.getY());
					}
				}
			}
		});

		JScrollPane spUser = new JScrollPane(lstUser);
		spUser.setFont(new Font("宋体", 0, 16));
		spUser.setBounds(0, 0,160,450);
		spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spUser.setBounds(0, 0,160,450);
		spUser.setOpaque(false);
		pnlUser.add(spUser);


		JLabel button11 = new JLabel(user.getUsername());

		//QButton21表示的是商城
		ImageIcon shopImage = new ImageIcon("./Icon\\shop2.png");
		JButton button21 = new JButton(shopImage);
		button21.setContentAreaFilled(false);

		//QButton22表示的是发送按钮
		JButton button22 = new JButton("发送");

		//QButton23—26表示的是表情包、发送文件、图片、保存按钮和抖一抖按钮
		ImageIcon expressionImage = new ImageIcon("./Icon\\expression2.png");
		JButton button23 = new JButton(expressionImage);
		button23.setContentAreaFilled(false);

		ImageIcon fileImage = new ImageIcon("./Icon\\file2.png");
		JButton button24 = new JButton(fileImage);
		button24.setContentAreaFilled(false);

		ImageIcon pictureImage = new ImageIcon("./Icon\\picture2.png");
		JButton button25 = new JButton(pictureImage);
		button25.setContentAreaFilled(false);

//		JButton button26 = new JButton("保存");

		ImageIcon chuoImage = new ImageIcon("./Icon\\chuo2.png");
		JButton button27 = new JButton(chuoImage);
		button27.setContentAreaFilled(false);

		//设置修改字体的下拉框
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] typeface = graphicsEnvironment.getAvailableFontFamilyNames();
		cb = new JComboBox(typeface);
		for(String string:typeface)
		{
			cb.addItem(string);
		}
		cb.setSelectedItem("楷体");
		cb.setBounds(276, 510, 80, 30);

		//1表示的是对话框的文本框，2表示的是输入框的文本框
		acceptPane= new JTextPane();
		acceptPane.setBounds(0, 0, 520, 450);
		acceptPane.setOpaque(false);
		acceptPane.setEditable(false);
		acceptPane.setFont(new Font("宋体", 0, 20));
		text_background.setBounds(190 , 50, 520, 450);

		JTextPane sendPane = new JTextPane();
		sendPane.setBounds(0, 0, 520, 140);
		sendPane.setFont(new Font("宋体", 0, 20));

		//给接收框和输入框加滚轮
		JScrollPane spReceive = new JScrollPane(acceptPane);
		spReceive.setBounds(0, 0, 520, 450);
		spReceive.setOpaque(false);
		spReceive.getViewport().setOpaque(false);
		//jpReceive.add(spReceive);

		JPanel jpInput = new JPanel();
		jpInput.setLayout(null);
		jpInput.setOpaque(false);
		jpInput.setBounds(190, 550, 520, 140);
		JScrollPane spInput = new JScrollPane(sendPane);
		spInput.setBounds(0, 0, 520, 140);
		jpInput.add(spInput);

		//设置头像的位置
		button1.setBounds(10, 10 ,50, 50);

		button11.setBounds(70, 20, 100, 30);
		button11.setFont(new Font("宋体", 1, 18));
		//设置用户名的位置

		//设置商城的位置
		button21.setBounds(20, 550, 140, 140);

		//设置发送按钮的位置
		button22.setBounds(640, 510, 60, 30);
		button22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TransferInfo tif = new TransferInfo();
				//包装了所有文字对应的属性
				List<FontStyle> fontStyles = FontSupport.fontEncode(sendPane);
				tif.setContent(fontStyles);
				//发送人
				tif.setSender(account);
				String reciver = "All";
				//我们要获取到，当前要发送给谁？
				Object reciverObj =reciverBox_ChatFrame.getSelectedItem();
				if(reciverObj != null) {
					reciver = String.valueOf(reciverObj);
				}
				//接收人
				tif.setReciver(reciver);
				//本次处理的消息类型为聊天
				tif.setStatusEnum(ChatStatus.CHAT);
				IOStream.writeMessage(socket, tif);
				sendPane.setText("");
			}
		});

		//设置表情包、发送文件、图片和保存按钮的位置
		button23.setBounds(190,510, 30,30);
		button24.setBounds(230,510, 38,30);
		button25.setBounds(230,510, 36,30);
//		button26.setBounds(640,20, 60,30);
		button27.setBounds(366,510, 47,30);

		button25.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser colorChooser=new JColorChooser();
				Color color=colorChooser.showDialog(ChatFrame.this,"Color",Color.BLACK);
				FontSupport.setFont(sendPane,color,cb.getSelectedItem().toString(),Font.BOLD,20);
			}
		});

		button27.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TransferInfo tfi = new TransferInfo();
				tfi.setStatusEnum(ChatStatus.DD);
				tfi.setSender(account);
				String reciver = "All";
				//我们要获取到，当前要发送给谁？
				Object reciverObj = reciverBox_ChatFrame.getSelectedItem();
				if(reciverObj != null) {
					reciver = String.valueOf(reciverObj);
				}
				//接收人
				tfi.setReciver(reciver);
				IOStream.writeMessage(socket, tfi);
			}
		});

		add(jl);

		jl.add(button1);
		jl.add(button11);
		jl.add(button21);
		jl.add(button22);
		jl.add(button23);
		jl.add(button25);
		jl.add(button27);

		jl.add(pnlUser);

		jl.add(cb);

		jl.add(friendList);

		jl.add(text_background);
		text_background.add(spReceive);

		jl.add(jpInput);

		reciverBox_ChatFrame=new JComboBox();
		reciverBox_ChatFrame.setSelectedItem("All");
		reciverBox_ChatFrame.addItem("All");

		setVisible(true);

		//给自己的头像按钮增加监听(打开自己的个人信息页面)
		button1.addActionListener(new ActionListener(){

			//当按钮被点击时，就会触发 ActionEvent 事件
			//actionPerformed 方法就会被执行
			@Override
			public void actionPerformed(ActionEvent e){
				personal_information Pi = new personal_information(account,socket);
			}
		});

		//给商店增加监听
		button21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Store_window store  = new Store_window();
			}
		});

		//给表情包增加监听
		button23.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Expression_dialog expression  = new Expression_dialog(sendPane);
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					System.out.println(account + "窗口关闭");
					TransferInfo tfi = new TransferInfo();
					tfi.setStatusEnum(ChatStatus.QUIT);
					tfi.setAccount(account);
					tfi.setNotice(account + "已离开聊天室.....");
					IOStream.writeMessage(socket, tfi);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
