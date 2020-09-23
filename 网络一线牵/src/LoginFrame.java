import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = -4065664344081690485L;
	private static final Integer FRAME_WIDTH = 400;
	private static final Integer FRAME_HEIGHT = 300;
	private static final String SERVER_IP="localhost";
	private static final Integer PORT=6666;
	String statu="";

	public LoginFrame()
	{
		this.setTitle("登录注册界面");
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		JPanel panel=new JPanel();
		JPanel panel2=new JPanel();
		JPanel cards=new JPanel(new CardLayout());
		int width=screenSize.width;
		int height=screenSize.height;
		setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);
		ImageIcon imageIcon=new ImageIcon("src/image/image_1.jpg");
		JLabel lblBackground=new JLabel(imageIcon);
		lblBackground.setBounds(0,0,FRAME_WIDTH, FRAME_HEIGHT);
		lblBackground.setLayout(null);
		this.add(lblBackground);
		ImageIcon accountImage = new ImageIcon("./wordart\\account2.png");
		JLabel lblUid=new JLabel(accountImage);
		lblUid.setBounds(40,40,120,30);
		//lblUid.setFont(new Font("宋体",0,16));
		lblUid.setForeground(Color.BLACK);
		lblBackground.add(lblUid);
		final JTextField textUid=new JTextField();
		textUid.setBounds(150,40,160,30);
		lblBackground.add(textUid);
		ImageIcon passwordImage = new ImageIcon("./wordart\\password2.png");
		JLabel lblPsw=new JLabel(passwordImage);
		lblPsw.setBounds(40, 80, 120, 30);
		//lblPsw.setFont(new Font("宋体" , 0 , 16));
		lblPsw.setForeground(Color.BLACK);
		lblBackground.add(lblPsw);
		final JPasswordField textPsw = new JPasswordField();
		textPsw.setBounds(150, 80, 160, 30);
		lblBackground.add(textPsw);
		JButton enter = new JButton("登 录");
		enter.setBounds(110, 170, 80, 25);
		lblBackground.add(enter);
		JButton register=new JButton("注册");
		register.setBounds(215,170,80,25);
		lblBackground.add(register);
		panel.add(lblBackground);


		ImageIcon background=new ImageIcon("src/image/image_2.jpg");
		JLabel background_label=new JLabel(background);
		background_label.setBounds(0,0,500,500);

		//昵称
		ImageIcon nameImage = new ImageIcon("./wordart\\word7.png");
		//账号
		ImageIcon AccountImage = new ImageIcon("./wordart\\word9.png");
		//性别
		ImageIcon sexImage = new ImageIcon("./wordart\\word5.png");
		//年龄
		ImageIcon ageImage = new ImageIcon("./wordart\\word11.png");
		//注册图标
		ImageIcon registerImage = new ImageIcon("./wordart\\register2.png");

		JLabel label_1=new JLabel(registerImage);
		label_1.setBounds(210,10,60,40);
//		label_1.setFont(new Font("宋体" , 0 , 25));
//		label_1.setForeground(Color.BLACK);
		JLabel label_2=new JLabel(AccountImage);
		JLabel label_3=new JLabel(passwordImage);
		JLabel label_4=new JLabel(nameImage);
		JLabel label_5=new JLabel(sexImage);
		JLabel label_6=new JLabel(ageImage);
		JButton register_btn=new JButton("提交");
		JTextField account_text=new JTextField();
		JTextField password_text=new JTextField();
		JTextField username_text=new JTextField();
		JTextField sex_text=new JTextField();
		JTextField age_text=new JTextField();
//		register_btn.setFont(new Font("宋体" , 0 , 25));
//		register_btn.setForeground(Color.BLACK);
//		label_2.setFont(new Font("宋体" , 0 , 25));
//		label_2.setForeground(Color.BLACK);
//		label_3.setFont(new Font("宋体" , 0 , 25));
//		label_3.setForeground(Color.BLACK);
//		label_4.setFont(new Font("宋体" , 0 , 25));
//		label_4.setForeground(Color.BLACK);
//		label_5.setFont(new Font("宋体" , 0 , 25));
//		label_5.setForeground(Color.BLACK);
//		label_6.setFont(new Font("宋体" , 0 , 25));
//		label_6.setForeground(Color.BLACK);
		label_2.setBounds(40,50,100,50);
		label_3.setBounds(40,100,100,50);
		label_4.setBounds(40,150,100,50);
		label_5.setBounds(40,200,100,50);
		label_6.setBounds(40,250,100,50);
		register_btn.setBounds(200, 320,60,30);
		account_text.setBounds(150,60,250,30);
		password_text.setBounds(150,110,250,30);
		username_text.setBounds(150,160,250,30);
		sex_text.setBounds(150,210,250,30);
		age_text.setBounds(150,260,250,30);
		background_label.add(label_2);
		background_label.add(label_3);
		background_label.add(label_4);
		background_label.add(label_5);
		background_label.add(label_6);
		background_label.add(register_btn);
		background_label.add(label_1);
		background_label.add(account_text);
		background_label.add(password_text);
		background_label.add(username_text);
		background_label.add(sex_text);
		background_label.add(age_text);

		panel2.add(background_label);
		cards.add(panel,"card1");
		cards.add(panel2,"card2");
		CardLayout c1=(CardLayout)(cards.getLayout());
		c1.show(cards,"card1");
		this.add(cards);
		setVisible(true);
		setDefaultCloseOperation(2);

		//注册
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(500,100,500,400);
				c1.show(cards,"card2");
			}
		});

		register_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2,FRAME_WIDTH,FRAME_HEIGHT);
				c1.show(cards,"card1");

				TransferInfo tif = new TransferInfo();
				tif.setAccount(account_text.getText());
				tif.setPassword(password_text.getText());
				tif.setUserName(username_text.getText());
				tif.setSex(sex_text.getText());
				tif.setAge(age_text.getText());

				//本次处理的消息类型为注册
				tif.setStatusEnum(ChatStatus.REGESTER);
				statu="注册"+account_text.getText()+"#"+new String(password_text.getText())+"#";
				connectionServer(tif);
				System.out.println(statu);
			}
		});

		//登陆
		enter.addActionListener(actionEvent -> {
			TransferInfo tif = new TransferInfo();
			tif.setAccount(textUid.getText());
			tif.setPassword(new String(textPsw.getPassword()));
			//本次处理的消息类型为登录
			tif.setStatusEnum(ChatStatus.LOGIN);
			statu="登陆"+textUid.getText()+"#"+new String(textPsw.getPassword())+"#";
			if(!"".equals(statu)){
				connectionServer(tif);
			}
			System.out.println(statu);
		});
	}

	//连接服务器的方法
	public void connectionServer(TransferInfo tif){
		try {
			System.out.println("连接之前");
			Socket socket=new Socket(SERVER_IP,PORT);

			IOStream.writeMessage(socket,tif);

			ClientHandler client=new ClientHandler(socket,this);
			client.start();
			System.out.println("连接之后");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		new LoginFrame();
	}
}

