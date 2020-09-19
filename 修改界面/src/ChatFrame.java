import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.List;

import javax.swing.*;

public class ChatFrame extends JFrame{
    /*private static final long serialVersionUID = -8945833334986986964L;


    public static final Integer FRAME_WIDTH = 750;


    public static final Integer FRAME_HEIGHT = 600;

    //接收框
    public JTextPane acceptPane;

    //当前在线用户列表
    public JList lstUser;

    String userName;

    Socket socket;

    ChatFrame chatFrame;

    //Java中变量作用域
    JComboBox reciverBox;

    //字体
    JComboBox fontFamilyCmb;

    public ChatFrame(String userName,Socket socket) {

        chatFrame = this;

        this.socket = socket;
        this.userName = userName;

        this.setTitle("聊天室主界面");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //窗体不可扩大
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //获取屏幕
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        //屏幕居中处理
        setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);

        //加载窗体的背景图片
        ImageIcon imageIcon = new ImageIcon("src/image/beijing.jpg");
        //创建一个标签并将图片添加进去
        JLabel frameBg = new JLabel(imageIcon);
        //设置图片的位置和大小
        frameBg.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        this.add(frameBg);

        // 接收框
        acceptPane = new JTextPane();
        acceptPane.setOpaque(false);//设置透明
        acceptPane.setFont(new Font("宋体", 0, 16));

        // 设置接收框滚动条
        JScrollPane scoPaneOne = new JScrollPane(acceptPane);
        scoPaneOne.setBounds(15, 20, 500, 332);
        //设置背景透明
        scoPaneOne.setOpaque(false);
        scoPaneOne.getViewport().setOpaque(false);
        frameBg.add(scoPaneOne);


        //当前在线用户列表
        lstUser = new JList();
        lstUser.setFont(new Font("宋体", 0, 14));
        lstUser.setVisibleRowCount(17);
        lstUser.setFixedCellWidth(180);
        lstUser.setFixedCellHeight(60);


        //声明菜单
        JPopupMenu popupMenu = new JPopupMenu();

        //私聊按钮（菜单项）
        JMenuItem privateChat = new JMenuItem("私聊");
        privateChat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //做一系列的动作，那这个动作包含了什么呢？
                //伪代码，重要的是逻辑能力，业务逻辑
                //告诉发送消息的接收人是谁？
                //
                Object reciverObj = lstUser.getSelectedValue();
                if(reciverObj instanceof User) {
                    User user = (User)reciverObj;
                    String reciver = user.getAccount();
                    reciverBox.removeAllItems();
                    reciverBox.addItem("All");
                    reciverBox.addItem(reciver);
                    reciverBox.setSelectedItem(reciver);
                }

            }
        });
        popupMenu.add(privateChat);


        //黑名单按钮（菜单项）
        JMenuItem blackList = new JMenuItem("黑名单");
        blackList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        popupMenu.add(blackList);


        //添加点击事件，（需要确认是右键）
        lstUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //监听是左键还是邮件
                if(e.isMetaDown()) {
                    if(lstUser.getSelectedIndex() >= 0) {
                        //弹出菜单,JavaScript JS
                        popupMenu.show(lstUser , e.getX() , e.getY());
                    }
                }
            }
        });

        JScrollPane spUser = new JScrollPane(lstUser);
        spUser.setFont(new Font("宋体", 0, 14));
        spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spUser.setBounds(530, 17, 200, 507);
        frameBg.add(spUser);


        // 输入框
        JTextPane sendPane = new JTextPane();
        sendPane.setOpaque(false);
        sendPane.setFont(new Font("宋体", 0, 16));

        JScrollPane scoPane = new JScrollPane(sendPane);// 设置滚动条
        scoPane.setBounds(15, 400, 500, 122);
        scoPane.setOpaque(false);
        scoPane.getViewport().setOpaque(false);
        frameBg.add(scoPane);

        // 添加表情选择
        JLabel lblface = new JLabel(new ImageIcon("src/image/face.png"));
        lblface.setBounds(14, 363, 25, 25);
        lblface.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FaceFrame face = new FaceFrame(sendPane);
            }
        });
        frameBg.add(lblface);

        // 添加抖动效果
        JLabel lbldoudong = new JLabel(new ImageIcon("src/image/doudong.png"));
        lbldoudong.setBounds(43, 363, 25, 25);
        lbldoudong.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //抖动功能实现
                TransferInfo tfi = new TransferInfo();
                tfi.setStatusEnum(ChatStatus.DD);
                tfi.setSender(userName);
                String reciver = "All";
                //我们要获取到，当前要发送给谁？
                Object reciverObj = reciverBox.getSelectedItem();
                if(reciverObj != null) {
                    reciver = String.valueOf(reciverObj);
                }
                //接收人
                tfi.setReciver(reciver);
                IOStream.writeMessage(socket, tfi);
            }

        });
        frameBg.add(lbldoudong);

        // 设置字体选择
        JLabel lblfontChoose = new JLabel(new ImageIcon("src/image/ziti.png"));
        lblfontChoose.setBounds(44, 363, 80, 25);
        lblfontChoose.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JColorChooser colorChooser = new JColorChooser();
                Color color = colorChooser.showDialog(ChatFrame.this, "字体颜色", Color.BLACK);
                //字体改变
                FontSupport.setFont(sendPane, color, fontFamilyCmb.getSelectedItem().toString(), Font.BOLD, 60);
            }

        });
        frameBg.add(lblfontChoose);

        //字体下拉选项
        fontFamilyCmb = new JComboBox();
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        String[] str = graphicsEnvironment.getAvailableFontFamilyNames();
        for (String string : str) {
            fontFamilyCmb.addItem(string);
        }
        fontFamilyCmb.setSelectedItem("楷体");
        fontFamilyCmb.setBounds(104, 363, 150, 25);
        frameBg.add(fontFamilyCmb);

        //label标签
        JLabel reciverLabel = new JLabel("聊天对象");
        reciverLabel.setBounds(304, 363, 80, 25);
        frameBg.add(reciverLabel);

        //下拉选择框
        reciverBox = new JComboBox();
        reciverBox.setSelectedItem("All");
        reciverBox.addItem("All");
        reciverBox.setBounds(374, 363, 150, 25);
        frameBg.add(reciverBox);


        JButton send = new JButton("发 送");
        send.setBounds(15, 533, 125, 25);
        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TransferInfo tif = new TransferInfo();
                //包装了所有文字对应的属性
                List<FontStyle> fontStyles = FontSupport.fontEncode(sendPane);
                tif.setContent(fontStyles);
                //发送人
                tif.setSender(userName);
                String reciver = "All";
                //我们要获取到，当前要发送给谁？
                Object reciverObj = reciverBox.getSelectedItem();
                if(reciverObj != null) {
                    reciver = String.valueOf(reciverObj);
                }
                //接收人
                tif.setReciver(reciver);
                //本次处理的消息类型为登录
                tif.setStatusEnum(ChatStatus.CHAT);
                IOStream.writeMessage(socket, tif);
                sendPane.setText("");
            }
        });
        frameBg.add(send);

        JButton fileBtn = new JButton("传文件");
        fileBtn.setBounds(205, 533, 125, 25);
        fileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reciver = "All";
                //我们要获取到，当前要发送给谁？
                Object reciverObj = reciverBox.getSelectedItem();
                if(reciverObj != null) {
                    reciver = String.valueOf(reciverObj);
                    //文件发送框
                    FileSendFrame fileSendFrame = new FileSendFrame(socket , reciver , userName);
                }else {
                    JOptionPane.showMessageDialog(null, "请右键私聊某一用户后使用该功能");
                }

            }
        });
        frameBg.add(fileBtn);
        //客户端关闭窗体退出
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    System.out.println(userName + "窗口关闭");
                    TransferInfo tfi = new TransferInfo();
                    tfi.setStatusEnum(ChatStatus.QUIT);
                    tfi.setUserName(userName);
                    tfi.setNotice(userName + "已离开聊天室.....");
                    IOStream.writeMessage(socket, tfi);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        setVisible(true);
    }*/
    public static final Integer FRAME_WIDTH = 730;
    public static final Integer FRAME_HEIGHT = 730;
    JComboBox reciverBox_ChatFrame;
    public JList lstUser;
    String account;
    Socket socket;
    ChatFrame chatFrame;
    static ImageIcon text_image = new ImageIcon("./chatting_background\\background1(2).jpg");
    public static JLabel text_background = new JLabel(text_image);
    JComboBox cb;
    public JTextPane acceptPane;
    //JButton1是本账号的头像
    public static ImageIcon headPicture = new ImageIcon("./head_portrait\\head1(2).png");
    public static JButton button1 = new JButton(headPicture);
    Private_chat_window private_chat_window;

    public ChatFrame(String account, Socket socket)
    {


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
        JLabel onlineNumber = new JLabel( "n人在线");
        onlineNumber.setBounds(440, 20, 70, 30);

        //JButton1-10表示对话框里面展示的头像（方向为从上到下，第一个是本账号的头像）
        //JButton button1 = new JButton();
        /*JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JButton button5 = new JButton();
        JButton button6 = new JButton();
        JButton button7 = new JButton();
        JButton button8 = new JButton();
        JButton button9 = new JButton();
        JButton button10 = new JButton();*/

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
                System.out.println("11111111111111111111111111111111");
                private_chat_window=new Private_chat_window(account,socket);
                if(reciverObj instanceof User)
                {
                    User user = (User)reciverObj;
                    String reciver=user.getAccount();
                    reciverBox_ChatFrame.removeAllItems();
                    reciverBox_ChatFrame.addItem("All");
                    reciverBox_ChatFrame.addItem(reciver);
                    reciverBox_ChatFrame.setSelectedItem(reciver);

                    Private_chat_window.reciverBox.removeAllItems();
                    Private_chat_window.reciverBox.addItem("All");
                    Private_chat_window.reciverBox.addItem(reciver);
                    Private_chat_window.reciverBox.setSelectedItem(reciver);
                }
                System.out.println("22222222222222222222222");
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

        //JButton11—20是用户昵称（方向为从上至下，第一个是账号号主的昵称）
        /*JLabel button11 = new JLabel();
        JLabel button12 = new JLabel();
        JLabel button13 = new JLabel();
        JLabel button14 = new JLabel();
        JLabel button15 = new JLabel();
        JLabel button16 = new JLabel();
        JLabel button17 = new JLabel();
        JLabel button18 = new JLabel();
        JLabel button19 = new JLabel();
        JLabel button20 = new JLabel();*/

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

        JButton button26 = new JButton("保存");

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
        /*JPanel jpReceive = new JPanel();
        jpReceive.setLayout(null);
        jpReceive.setOpaque(false);
        jpReceive.setBounds(0, 0, 520, 450);*/
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
        /*button2.setBounds(10, 90 ,40, 40);
        button3.setBounds(10, 140 ,40, 40);
        button4.setBounds(10, 190 ,40, 40);
        button5.setBounds(10, 240 ,40, 40);
        button6.setBounds(10, 290 ,40, 40);
        button7.setBounds(10, 340 ,40, 40);
        button8.setBounds(10, 390 ,40, 40);
        button9.setBounds(10, 440 ,40, 40);
        button10.setBounds(10, 490 ,40, 40);*/

        //设置用户名的位置
        /*button11.setBounds(70, 20, 100, 30);
        button12.setBounds(60, 100, 100, 30);
        button13.setBounds(60, 150, 100, 30);
        button14.setBounds(60, 200, 100, 30);
        button15.setBounds(60, 250, 100, 30);
        button16.setBounds(60, 300, 100, 30);
        button17.setBounds(60, 350, 100, 30);
        button18.setBounds(60, 400, 100, 30);
        button19.setBounds(60, 450, 100, 30);
        button20.setBounds(60, 500, 100, 30);*/

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
                //本次处理的消息类型为登录
                tif.setStatusEnum(ChatStatus.CHAT);
                IOStream.writeMessage(socket, tif);
                sendPane.setText("");
            }
        });

        //设置表情包、发送文件、图片和保存按钮的位置
        button23.setBounds(190,510, 30,30);
        button24.setBounds(230,510, 38,30);
        button25.setBounds(230,510, 36,30);
        button26.setBounds(640,20, 60,30);
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
        /*jl.add(button2);
        jl.add(button3);
        jl.add(button4);
        jl.add(button5);
        jl.add(button5);
        jl.add(button6);
        jl.add(button7);
        jl.add(button8);
        jl.add(button9);
        jl.add(button10);
        jl.add(button11);
        jl.add(button12);
        jl.add(button13);
        jl.add(button14);
        jl.add(button15);
        jl.add(button16);
        jl.add(button17);
        jl.add(button18);
        jl.add(button19);
        jl.add(button20);*/
        jl.add(button21);
        jl.add(button22);
        jl.add(button23);
        jl.add(button25);
        jl.add(button26);
        jl.add(button27);

        jl.add(pnlUser);

        jl.add(cb);

        jl.add(friendList);
        jl.add(onlineNumber);

        jl.add(text_background);
        text_background.add(spReceive);

        jl.add(jpInput);

        reciverBox_ChatFrame=new JComboBox();
        reciverBox_ChatFrame.setSelectedItem("All");
        reciverBox_ChatFrame.addItem("All");
        reciverBox_ChatFrame.setBounds(450,510,150,30);
        jl.add(reciverBox_ChatFrame);

        setVisible(true);

        //给自己的头像按钮增加监听(打开自己的个人信息页面)
        button1.addActionListener(new ActionListener(){

            //当按钮被点击时，就会触发 ActionEvent 事件
            //actionPerformed 方法就会被执行
            public void actionPerformed(ActionEvent e){
                personal_information Pi = new personal_information();
            }
        });

        //给好友的头像按钮增加监听(打开悄悄话窗口)
        /*button2.addActionListener(new ActionListener(){

            //当按钮被点击时，就会触发 ActionEvent 事件
            //actionPerformed 方法就会被执行
            public void actionPerformed(ActionEvent e){
                Private_chat_window Pi = new Private_chat_window();
            }
        });*/

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