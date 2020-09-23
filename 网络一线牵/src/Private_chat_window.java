import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Private_chat_window extends JFrame {
    String account;
    Socket socket;
    private final static String oppositeIP="localhost";
    private final static int phonePort=5555;
    public static JComboBox reciverBox;

    //8个JLabel，分别表示头像，昵称，性别，年龄，个性签名
    JLabel jl1;
    JLabel jl2;

    ImageIcon sexImage = new ImageIcon("./wordart\\word19.png");
    JLabel jl3 = new JLabel(sexImage);
    JLabel jl4;

    ImageIcon ageImage = new ImageIcon("./wordart\\word11.png");
    JLabel jl5 = new JLabel(ageImage);
    JLabel jl6;

    ImageIcon personalImage = new ImageIcon("./wordart\\word17.png");
    JLabel jl7 = new JLabel(personalImage);
    JLabel jl8;

    //7个JButton，分别表示表情包，文件，图片，视频，语音，发送，举报
    ImageIcon expressionImage = new ImageIcon("./Icon\\expression2.png");
    JButton jb1 = new JButton(expressionImage);
    //jb1.setContentAreaFilled(false);

    ImageIcon fileImage = new ImageIcon("./Icon\\file2.png");
    JButton jb2 = new JButton(fileImage);
    //jb2.setContentAreaFilled(false);


    ImageIcon pictureImage = new ImageIcon("./Icon\\picture2.png");
    JButton jb3 = new JButton(pictureImage);
    //jb3.setContentAreaFilled(false);


    ImageIcon videoImage = new ImageIcon("./Icon\\video.png");
    JButton jb4 = new JButton(videoImage);
    //jb4.setContentAreaFilled(false);

    ImageIcon phoneImage = new ImageIcon("./Icon\\phone.png");
    JButton jb5 = new JButton(phoneImage);
    //jb5.setContentAreaFilled(false);

    JButton jb6 = new JButton("发送");
    JButton jb7 = new JButton("举报");

    //两个文本域(一个输入的文本域，一个交互的文本域)
    JTextPane in = new JTextPane();
    JTextPane out = new JTextPane();


    public Private_chat_window(String account, Socket socket)
    {
        this.socket = socket;
        this.account = account;

        //导入个人信息
        User user=new UserDaoImpl().loadData(account);
        jl1 = new JLabel(new ImageIcon("./head_portrait\\head"+user.getPicture()+"(3).png"));
        String name =user.getUsername();
        jl2 = new JLabel(name);

        jl4 = new JLabel(user.getSex());
        jl6 = new JLabel(user.getAge());
        jl8 = new JLabel(user.getMotto());


        //创建一个JLabel用于储存背景图片
        ImageIcon image = new ImageIcon("./chatting_background\\background3.jpg");
        JLabel background = new JLabel(image);
        background.setBounds(0, 0, 730, 730);

        reciverBox=new JComboBox();
        reciverBox.setSelectedItem("All");
        reciverBox.addItem("ALL");
//        reciverBox.setBounds(300,545,150, 25);
//        background.add(reciverBox);

        setTitle("悄悄话");
        setLocation(400, 60);
        setLayout(null);
        setSize(730, 750);
        out.setEditable(false);

        //设置JLabel的位置
        jl1.setBounds(20, 10, 80, 80);
        jl2.setBounds(110, 35, 100, 30);
        jl2.setFont(new Font("宋体", 1, 18));
        jl3.setBounds(220, 20, 60, 30);
        jl4.setBounds(285, 23, 80,30);
        jl4.setFont(new Font("宋体", 0, 20));
        jl5.setBounds(380, 20, 60, 30);
        jl6.setBounds(445, 23, 100, 30);
        jl6.setFont(new Font("宋体", 0, 20));
        jl7.setBounds(230, 60, 105, 30);
        jl8.setBounds(340, 62, 200, 30);
        jl8.setFont(new Font("宋体", 0, 20));

        //设置JButton的位置
        jb1.setBounds(10, 545, 30, 30);
        jb2.setBounds(60, 545, 38, 30);
        jb3.setBounds(118, 545, 36, 30);
        jb4.setBounds(174, 545, 30, 30);
        jb5.setBounds(224, 545, 30, 30);
        jb6.setBounds(640, 545, 60, 30);
        jb7.setBounds(650, 60, 60, 30);

        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("照镜子");
                new Thread(new VideoSet("localhost", Constants.SERVER_PORT)).start();
            }
        });

        //给发送增加监听
        jb6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferInfo tif = new TransferInfo();
                List<FontStyle> fontStyles = FontSupport.fontEncode(in);
                tif.setContent(fontStyles);
                tif.setSender(account);
                Object reciverObj = reciverBox.getSelectedItem();
                String reciver =String.valueOf(reciverObj);
                tif.setReciver(reciver);
                //本次处理的消息类型为聊天
                tif.setStatusEnum(ChatStatus.CHAT);
                IOStream.writeMessage(socket, tif);
                in.setText("");
            }
        });


        //给语音聊天按钮增加监听
        jb5.addActionListener(e->{
            TransferInfo tif = new TransferInfo();
            tif.setSender(account);
            Object reciverObj = reciverBox.getSelectedItem();
            String reciver =String.valueOf(reciverObj);
            tif.setReciver(reciver);
            tif.setStatusEnum(ChatStatus.PHONE);
            System.out.println("请求通话");
            IOStream.writeMessage(socket, tif);


            try {
                //在请求通话的客户端方搭建临时服务器
                ServerSocket serverSocket=new ServerSocket(phonePort);
                Socket socket1=serverSocket.accept();
                Capture capture=new Capture(socket1);
                capture.start();

//                tif.setSendSocket(socket1);
//                Playback player=new Playback(socket1);
//                player.start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });



            //发送文件
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reciver = "All";
                Object reciverObj = reciverBox.getSelectedItem();
                if(reciverObj != null)
                {
                    reciver = String.valueOf(reciverObj);
                    FileSendFrame fileSendFrame = new FileSendFrame(socket , reciver , account);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "请右键私聊");
                }
            }
        });



        //给表情包增加监听
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Expression_dialog expression  = new Expression_dialog(in);
            }
        });

        //给举报按钮增加监听
        jb7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tip_offWindow tp = new Tip_offWindow((reciverBox.getSelectedItem()).toString(), socket, account);
            }
        });

        //设置前五个图标背景透明
        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);
        jb3.setContentAreaFilled(false);
        jb4.setContentAreaFilled(false);
        jb5.setContentAreaFilled(false);

        //设置两个文本域的位置
        out.setBounds(10, 100, 695,440);
        out.setFont(new Font("宋体", 0, 20));
        in.setBounds(10, 580,695, 120);
        in.setFont(new Font("宋体", 0, 20));

        add(background);

        background.add(jl1);
        background.add(jl2);
        background.add(jl3);
        background.add(jl4);
        background.add(jl5);
        background.add(jl6);
        background.add(jl7);
        background.add(jl8);

        background.add(jb1);
        background.add(jb2);
        background.add(jb3);
        background.add(jb4);
        background.add(jb5);
        background.add(jb6);
        background.add(jb7);

        background.add(in);
        background.add(out);

        setResizable(false);

        setVisible(true);
    }
}
