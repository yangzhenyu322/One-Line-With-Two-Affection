import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public static void main(String[] args) throws Exception{
        //主窗口
        JFrame frame=new JFrame("网络一线牵登陆");
        //布局
        //登陆界面面板
        JPanel panel1 = new JPanel();
        Box b1=Box.createVerticalBox();    //创建纵向Box容器
        Box b2=Box.createHorizontalBox();    //创建横向Box容器
        Box b3=Box.createHorizontalBox();    //创建横向Box容器
        Box b4=Box.createHorizontalBox();    //创建横向Box容器

        //注册界面面板
        JPanel panel2 = new JPanel();
        Box b1_1= Box.createVerticalBox();
        Box b2_1=Box.createHorizontalBox();    //创建横向Box容器
        Box b3_1=Box.createHorizontalBox();    //创建横向Box容器
        //卡片布局面板
        JPanel cards = new JPanel(new CardLayout());

        //登陆界面UI组件
        //账号
        JLabel label1=new JLabel("账号：");
        JTextField field1=new JTextField();
        b2.add(label1);
        b2.add(field1);
        //密码
        JLabel label2=new JLabel("密码：");
        JTextField field2=new JTextField();
        b3.add(label2);
        b3.add(field2);
        //注册账号
        JButton button1=new JButton("注册");
        //修改密码
        JButton button2=new JButton("修改密码");
        b4.add(button1);
        b4.add(button2);
        //登陆按钮
        JButton button3=new JButton("登陆");
        b1.add(b2);
        b1.add(b3);
        b1.add(b4);
        b1.add(button3);

        //注册界面UI组件
        //账号
        JLabel label1_1=new JLabel("账号：");
        JTextField field1_1=new JTextField();
        b2_1.add(label1_1);
        b2_1.add(field1_1);
        //密码
        JLabel label2_1=new JLabel("密码：");
        JTextField field2_1=new JTextField();
        b3_1.add(label2_1);
        b3_1.add(field2_1);
        //注册按钮
        JButton registerBt=new JButton("立即注册");
        b1_1.add(b2_1);
        b1_1.add(b3_1);
        b1_1.add(registerBt);


        //pane1添加组件
        panel1.add(b1);


        //pane2添加组件
        panel2.add(b1_1);

        cards.add(panel1,"card1");
        cards.add(panel2,"card2");
        CardLayout cl=(CardLayout)(cards.getLayout());
        cl.show(cards,"card1");
        frame.add(cards);
        frame.setBounds(700,330,530,410);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //绑定事件
        //进入注册界面
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(cards,"card2");
            }
        });
        //注册后返回登陆界面
        registerBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(cards,"card1");
            }
        });





    }
}
