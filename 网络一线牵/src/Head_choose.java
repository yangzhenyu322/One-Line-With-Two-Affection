import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;
import java.net.Socket;

public class Head_choose extends JFrame{

    JFrame f = new JFrame("头像选择");

    ImageIcon b1Image = new ImageIcon("./head_portrait\\head1(3).png");
    JButton b1 = new JButton(b1Image);

    ImageIcon b2Image = new ImageIcon("./head_portrait\\head2(3).png");
    JButton b2 = new JButton(b2Image);

    ImageIcon b3Image = new ImageIcon("./head_portrait\\head3(3).png");
    JButton b3 = new JButton(b3Image);

    ImageIcon b4Image = new ImageIcon("./head_portrait\\head4(3).png");
    JButton b4 = new JButton(b4Image);

    ImageIcon b5Image = new ImageIcon("./head_portrait\\head5(3).png");
    JButton b5 = new JButton(b5Image);

    ImageIcon b6Image = new ImageIcon("./head_portrait\\head6(3).png");
    JButton b6 = new JButton(b6Image);

    public Head_choose(String account, Socket socket)
    {
        TransferInfo tif = new TransferInfo();
        tif.setAccount(account);
        //本次处理的消息类型为登录
        tif.setStatusEnum(ChatStatus.REFLRSH);

        f.setSize(290, 200);
        f.setLocation(890, 200);
        f.setResizable(false);
        //设置一个两行三列的网格布局
        f.setLayout(new GridLayout(2, 3));

        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(b6);

        f.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./head_portrait\\head1(4).png");
                ImageIcon image2 = new ImageIcon("./head_portrait\\head1(2).png");
                int option = JOptionPane.showConfirmDialog(f, "是否要设置该头像？");
                if(JOptionPane.OK_OPTION == option) {
                    //更新数据库
                    User user=new User();
                    user.setAccount(account);
                    user.setPicture("1");
                    if(new UserDaoImpl().savePicture(user)){
                        System.out.println("修改头像成功");
                        IOStream.writeMessage(socket,tif);
                    }else {
                        System.out.println("修改头像失败");
                    }

                    JOptionPane.showMessageDialog(f, "设置成功！");
                    personal_information.headPicture.setIcon(image);
                    ChatFrame.button1.setIcon(image2);
                    f.setVisible(false);
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./head_portrait\\head2(4).png");
                ImageIcon image2 = new ImageIcon("./head_portrait\\head2(2).png");
                int option = JOptionPane.showConfirmDialog(f, "是否要设置该头像？");
                if(JOptionPane.OK_OPTION == option) {
                    //更新数据库
                    User user=new User();
                    user.setAccount(account);
                    user.setPicture("2");
                    if(new UserDaoImpl().savePicture(user)){
                        System.out.println("修改头像成功");
                        IOStream.writeMessage(socket,tif);
                    }else {
                        System.out.println("修改头像失败");
                    }

                    JOptionPane.showMessageDialog(f, "设置成功！");
                    personal_information.headPicture.setIcon(image);
                    ChatFrame.button1.setIcon(image2);
                    f.setVisible(false);
                }
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./head_portrait\\head3(4).png");
                ImageIcon image2 = new ImageIcon("./head_portrait\\head3(2).png");
                int option = JOptionPane.showConfirmDialog(f, "是否要设置该头像？");
                if(JOptionPane.OK_OPTION == option) {
                    //更新数据库
                    User user=new User();
                    user.setAccount(account);
                    user.setPicture("3");
                    if(new UserDaoImpl().savePicture(user)){
                        System.out.println("修改头像成功");
                        IOStream.writeMessage(socket,tif);
                    }else {
                        System.out.println("修改头像失败");
                    }

                    JOptionPane.showMessageDialog(f, "设置成功！");
                    personal_information.headPicture.setIcon(image);
                    ChatFrame.button1.setIcon(image2);
                    f.setVisible(false);
                }
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./head_portrait\\head4(4).png");
                ImageIcon image2 = new ImageIcon("./head_portrait\\head4(2).png");
                int option = JOptionPane.showConfirmDialog(f, "是否要设置该头像？");
                if(JOptionPane.OK_OPTION == option) {
                    //更新数据库
                    User user=new User();
                    user.setAccount(account);
                    user.setPicture("4");
                    if(new UserDaoImpl().savePicture(user)){
                        System.out.println("修改头像成功");
                        IOStream.writeMessage(socket,tif);
                    }else {
                        System.out.println("修改头像失败");
                    }

                    JOptionPane.showMessageDialog(f, "设置成功！");
                    personal_information.headPicture.setIcon(image);
                    ChatFrame.button1.setIcon(image2);
                    f.setVisible(false);
                }
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./head_portrait\\head5(4).png");
                ImageIcon image2 = new ImageIcon("./head_portrait\\head5(2).png");
                int option = JOptionPane.showConfirmDialog(f, "是否要设置该头像？");
                if(JOptionPane.OK_OPTION == option) {
                    //更新数据库
                    User user=new User();
                    user.setAccount(account);
                    user.setPicture("5");
                    if(new UserDaoImpl().savePicture(user)){
                        System.out.println("修改头像成功");
                        IOStream.writeMessage(socket,tif);
                    }else {
                        System.out.println("修改头像失败");
                    }

                    JOptionPane.showMessageDialog(f, "设置成功！");
                    personal_information.headPicture.setIcon(image);
                    ChatFrame.button1.setIcon(image2);
                    f.setVisible(false);
                }
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./head_portrait\\head6(4).png");
                ImageIcon image2 = new ImageIcon("./head_portrait\\head6(2).png");
                int option = JOptionPane.showConfirmDialog(f, "是否要设置该头像？");
                if(JOptionPane.OK_OPTION == option) {
                    //更新数据库
                    User user=new User();
                    user.setAccount(account);
                    user.setPicture("6");
                    if(new UserDaoImpl().savePicture(user)){
                        System.out.println("修改头像成功");
                        IOStream.writeMessage(socket,tif);
                    }else {
                        System.out.println("修改头像失败");
                    }

                    JOptionPane.showMessageDialog(f, "设置成功！");
                    personal_information.headPicture.setIcon(image);
                    ChatFrame.button1.setIcon(image2);
                    f.setVisible(false);
                }
            }
        });
    }
}
