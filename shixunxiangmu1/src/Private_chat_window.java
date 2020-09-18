import javax.swing.*;

public class Private_chat_window {

    JFrame f3 = new JFrame("悄悄话");

    //8个JLabel，分别表示头像，昵称，性别，年龄，个性签名
    JLabel jl1 = new JLabel();
    JLabel jl2 = new JLabel();

    ImageIcon sexImage = new ImageIcon("./wordart\\word19.png");
    JLabel jl3 = new JLabel(sexImage);
    JLabel jl4 = new JLabel("男");

    ImageIcon ageImage = new ImageIcon("./wordart\\word11.png");
    JLabel jl5 = new JLabel(ageImage);
    JLabel jl6 = new JLabel("8");

    ImageIcon personalImage = new ImageIcon("./wordart\\word17.png");
    JLabel jl7 = new JLabel(personalImage);
    JLabel jl8 = new JLabel("快乐每一天");

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
    JTextArea in = new JTextArea();
    JTextArea out = new JTextArea();

    public Private_chat_window()
    {
        //创建一个JLabel用于储存背景图片
        ImageIcon image = new ImageIcon("./chatting_background\\background3.jpg");
        JLabel background = new JLabel(image);
        background.setBounds(0, 0, 730, 730);

        f3.setLocation(400, 60);
        f3.setLayout(null);
        f3.setSize(730, 750);

        //设置JLabel的位置
        jl1.setBounds(20, 20, 80, 80);
        jl2.setBounds(90, 40, 100, 40);
        jl3.setBounds(220, 20, 60, 30);
        jl4.setBounds(280, 20, 80,30);
        jl5.setBounds(380, 20, 60, 30);
        jl6.setBounds(440, 20, 100, 30);
        jl7.setBounds(230, 60, 100, 30);
        jl8.setBounds(330, 60, 200, 30);

        //设置JButton的位置
        jb1.setBounds(10, 545, 30, 30);
        jb2.setBounds(60, 545, 38, 30);
        jb3.setBounds(118, 545, 36, 30);
        jb4.setBounds(174, 545, 30, 30);
        jb5.setBounds(224, 545, 30, 30);
        jb6.setBounds(640, 670, 60, 30);
        jb7.setBounds(650, 60, 60, 30);

        //设置前五个图标背景透明
        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);
        jb3.setContentAreaFilled(false);
        jb4.setContentAreaFilled(false);
        jb5.setContentAreaFilled(false);

        //设置两个文本域的位置
        out.setBounds(10, 100, 695,440);
        in.setBounds(10, 580,695, 120);

        f3.add(background);

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

        f3.setResizable(false);

        f3.setVisible(true);
    }
}