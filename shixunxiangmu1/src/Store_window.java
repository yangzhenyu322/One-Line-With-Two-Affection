import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Store_window {
    public Store_window()
    {
        //创建Label用于存放图片，作为背景
        ImageIcon image1 = new ImageIcon("./Course\\1.png");
        ImageIcon image3 = new ImageIcon("./Course\\3.png");
        ImageIcon image2 = new ImageIcon("./Course\\2.png");
        JLabel j1 = new JLabel(image1);
        JLabel j2 = new JLabel(image2);
        JLabel j3 = new JLabel(image3);
        j2.setVisible(false);
        j3.setVisible(false);
        j1.setBounds(0,0,530,730);
        j2.setBounds(0,0,530,730);
        j3.setBounds(0,0,530,730);
        j1.setLayout(null);
        j2.setLayout(null);
        j3.setLayout(null);

        int n = 666;
        //主窗口
        JFrame f = new JFrame("商店");
        f.setLayout(null);
        f.setLocation(500, 60);
        f.setSize(530,730);
        f.setResizable(false);

        //持有源石数
        JButton money1  = new JButton("你目前拥有"+n+"源石");
        money1.setBounds(0,640,200,60);
        JButton money2  = new JButton("你目前拥有"+n+"源石");
        money2.setBounds(0,640,200,60);
        JButton money3  = new JButton("你目前拥有"+n+"源石");
        money3.setBounds(0,640,200,60);
        //下一页按钮
        JButton next1 = new JButton("下一页");
        next1.setBounds(400,640,100,60);
        JButton next2 = new JButton("下一页");
        next2.setBounds(400,640,100,60);
        //上一页按钮
        JButton last2 = new JButton("上一页");
        last2.setBounds(300,640,100,60);
        JButton last3 = new JButton("上一页");
        last3.setBounds(300,640,100,60);
        //
        //创建商品项目1
        ImageIcon item_image1 = new ImageIcon("./Course\\item1.png");
        JButton item1  = new JButton(item_image1);
        item1.setBounds(20,20,220,220);
        JButton buy1 = new JButton("绿野仙踪:15源石");
        buy1.setBounds(20,250,220,40);

        //创建商品项目2
        ImageIcon item_image2 = new ImageIcon("./Course\\item2.png");
        JButton item2  = new JButton(item_image2);
        item2.setBounds(260,20,220,220);
        JButton buy2 = new JButton("往昔回忆:15源石");
        buy2.setBounds(260,250,220,40);

        //创建商品项目3
        ImageIcon item_image3 = new ImageIcon("./Course\\item3.png");
        JButton item3  = new JButton(item_image3);
        item3.setBounds(20,320,220,220);
        JButton buy3 = new JButton("洋流:30源石");
        buy3.setBounds(20,550,220,40);

        //创建商品项目4
        ImageIcon item_image4 = new ImageIcon("./Course\\item4.png");
        JButton item4  = new JButton(item_image4);
        item4.setBounds(260,320,220,220);
        JButton buy4 = new JButton("Futari Dake No Hanabi:30源石");
        buy4.setBounds(260,550,220,40);
//以下为第二页
        //创建商品项目5
        ImageIcon item_image5 = new ImageIcon("./Course\\item5.png");
        JButton item5  = new JButton(item_image5);
        item5.setBounds(20,20,220,220);
        JButton buy5 = new JButton("明日方舟:15源石");
        buy5.setBounds(20,250,220,40);

        //创建商品项目6
        ImageIcon item_image6 = new ImageIcon("./Course\\item6.png");
        JButton item6  = new JButton(item_image6);
        item6.setBounds(260,20,220,220);
        JButton buy6 = new JButton("Sekiro:15源石");
        buy6.setBounds(260,250,220,40);

        //创建商品项目7
        ImageIcon item_image7 = new ImageIcon("./Course\\item7.png");
        JButton item7  = new JButton(item_image7);
        item7.setBounds(20,320,220,220);
        JButton buy7 = new JButton("Fate/grand Order:30源石");
        buy7.setBounds(20,550,220,40);

        //创建商品项目8
        ImageIcon item_image8 = new ImageIcon("./Course\\item8.png");
        JButton item8  = new JButton(item_image8);
        item8.setBounds(260,320,220,220);
        JButton buy8 = new JButton("Sakura:30源石");
        buy8.setBounds(260,550,220,40);

//以下为第三页
        //项目9
        ImageIcon item_image9 = new ImageIcon("./Course\\item9.png");
        JButton item9  = new JButton(item_image9);
        item9.setBounds(20,20,220,220);
        JButton buy9 = new JButton("老大哥:15源石");
        buy9.setBounds(20,250,220,40);

        //创建商品项目10
        ImageIcon item_image10 = new ImageIcon("./Course\\item10.png");
        JButton item10  = new JButton(item_image10);
        item10.setBounds(260,20,220,220);
        JButton buy10 = new JButton("香蕉君:15源石");
        buy10.setBounds(260,250,220,40);

        j1.add(buy1);
        j1.add(item1);
        j1.add(buy2);
        j1.add(item2);
        j1.add(buy3);
        j1.add(item3);
        j1.add(buy4);
        j1.add(item4);
        j1.add(money1);
        j1.add(next1);

        j2.add(buy5);
        j2.add(buy6);
        j2.add(buy7);
        j2.add(buy8);
        j2.add(item5);
        j2.add(item6);
        j2.add(item7);
        j2.add(item8);
        j2.add(money2);
        j2.add(last2);
        j2.add(next2);

        j3.add(buy9);
        j3.add(item9);
        j3.add(buy10);
        j3.add(item10);
        j3.add(last3);
        j3.add(money3);
        f.add(j1);
        f.add(j2);
        f.add(j3);
        f.setVisible(true);

        buy4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(f,"你是否要购买该商品？");
                if(JOptionPane.OK_OPTION== option){
                    JOptionPane.showMessageDialog(f,"购买成功！");
                    money1.setText("你目前拥有"+636+"源石");
                    money2.setText("你目前拥有"+636+"源石");
                    money3.setText("你目前拥有"+636+"源石");
                    buy4.setText("商品4:已拥有(点击皮肤设置)");

                }

            }
        });

        next1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                j1.setVisible(false);
                j2.setVisible(true);
                j3.setVisible(false);
            }
        });
        next2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                j1.setVisible(false);
                j2.setVisible(false);
                j3.setVisible(true);
            }
        });

        last2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                j1.setVisible(true);
                j2.setVisible(false);
                j3.setVisible(false);
            }
        });
        last3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                j1.setVisible(false);
                j2.setVisible(true);
                j3.setVisible(false);
            }
        });

        //给item1添加换肤监听
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./Course\\item4(2).png");
                chatting_window.text_background.setIcon(image);
            }
        });

        //给item2添加换肤监听
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./Course\\item4(2).png");
                chatting_window.text_background.setIcon(image);
            }
        });

        //给item3添加换肤监听
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./Course\\item4(2).png");
                chatting_window.text_background.setIcon(image);
            }
        });

        //给item4添加换肤监听
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon image = new ImageIcon("./Course\\item4(2).png");
                chatting_window.text_background.setIcon(image);
            }
        });

    }
}
