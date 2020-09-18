import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;

public class Expression_dialog extends JFrame{

    JFrame d = new JFrame("表情包");

    //24个表情包
    ImageIcon b1Image = new ImageIcon("./expression\\e1.png");
    JButton button1 = new JButton(b1Image);

    ImageIcon b2Image = new ImageIcon("./expression\\e2.png");
    JButton button2 = new JButton(b2Image);

    ImageIcon b3Image = new ImageIcon("./expression\\e3.png");
    JButton button3 = new JButton(b3Image);

    ImageIcon b4Image = new ImageIcon("./expression\\e4.png");
    JButton button4 = new JButton(b4Image);

    ImageIcon b5Image = new ImageIcon("./expression\\e5.png");
    JButton button5 = new JButton(b5Image);

    ImageIcon b6Image = new ImageIcon("./expression\\e6.png");
    JButton button6 = new JButton(b6Image);

    ImageIcon b7Image = new ImageIcon("./expression\\e7.png");
    JButton button7 = new JButton(b7Image);

    ImageIcon b8Image = new ImageIcon("./expression\\e8.png");
    JButton button8 = new JButton(b8Image);

    ImageIcon b9Image = new ImageIcon("./expression\\e9.png");
    JButton button9 = new JButton(b9Image);

    ImageIcon b10Image = new ImageIcon("./expression\\e10.png");
    JButton button10 = new JButton(b10Image);

    ImageIcon b11Image = new ImageIcon("./expression\\e11.png");
    JButton button11 = new JButton(b11Image);

    ImageIcon b12Image = new ImageIcon("./expression\\e12.png");
    JButton button12 = new JButton(b12Image);

    ImageIcon b13Image = new ImageIcon("./expression\\e13.png");
    JButton button13 = new JButton(b13Image);

    ImageIcon b14Image = new ImageIcon("./expression\\e14.png");
    JButton button14 = new JButton(b14Image);

    ImageIcon b15Image = new ImageIcon("./expression\\e15.png");
    JButton button15 = new JButton(b15Image);

    ImageIcon b16Image = new ImageIcon("./expression\\e16.png");
    JButton button16 = new JButton(b16Image);

    ImageIcon b17Image = new ImageIcon("./expression\\e17.png");
    JButton button17 = new JButton(b17Image);

    ImageIcon b18Image = new ImageIcon("./expression\\e18.png");
    JButton button18 = new JButton(b18Image);

    ImageIcon b19Image = new ImageIcon("./expression\\e19.png");
    JButton button19 = new JButton(b19Image);

    ImageIcon b20Image = new ImageIcon("./expression\\e20.png");
    JButton button20 = new JButton(b20Image);

    ImageIcon b21Image = new ImageIcon("./expression\\e21.png");
    JButton button21 = new JButton(b21Image);

    ImageIcon b22Image = new ImageIcon("./expression\\e22.png");
    JButton button22 = new JButton(b22Image);

    ImageIcon b23Image = new ImageIcon("./expression\\e23.png");
    JButton button23 = new JButton(b23Image);

    ImageIcon b24Image = new ImageIcon("./expression\\e24.png");
    JButton button24 = new JButton(b24Image);

    public Expression_dialog(JTextPane textArea2)
    {
        d.setSize(240, 180);
        d.setLocation(590, 425);
        //设置一个四行六列的网格布局
        d.setLayout(new GridLayout(4, 6));

        d.add(button1);
        d.add(button2);
        d.add(button3);
        d.add(button4);
        d.add(button5);
        d.add(button6);
        d.add(button7);
        d.add(button8);
        d.add(button9);
        d.add(button10);
        d.add(button11);
        d.add(button12);
        d.add(button13);
        d.add(button14);
        d.add(button15);
        d.add(button16);
        d.add(button17);
        d.add(button18);
        d.add(button19);
        d.add(button20);
        d.add(button21);
        d.add(button22);
        d.add(button23);
        d.add(button24);

        d.setVisible(true);

        d.setResizable(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_1=(JButton)e.getSource();
                Icon icon_1=jButton_1.getIcon();
                textArea2.insertIcon(icon_1);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_2=(JButton)e.getSource();
                Icon icon_2=jButton_2.getIcon();
                textArea2.insertIcon(icon_2);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_3=(JButton)e.getSource();
                Icon icon_3=jButton_3.getIcon();
                textArea2.insertIcon(icon_3);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_4=(JButton)e.getSource();
                Icon icon_4=jButton_4.getIcon();
                textArea2.insertIcon(icon_4);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_5=(JButton)e.getSource();
                Icon icon_5=jButton_5.getIcon();
                textArea2.insertIcon(icon_5);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_6=(JButton)e.getSource();
                Icon icon_6=jButton_6.getIcon();
                textArea2.insertIcon(icon_6);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_7=(JButton)e.getSource();
                Icon icon_7=jButton_7.getIcon();
                textArea2.insertIcon(icon_7);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_8=(JButton)e.getSource();
                Icon icon_8=jButton_8.getIcon();
                textArea2.insertIcon(icon_8);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_9=(JButton)e.getSource();
                Icon icon_9=jButton_9.getIcon();
                textArea2.insertIcon(icon_9);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_10=(JButton)e.getSource();
                Icon icon_10=jButton_10.getIcon();
                textArea2.insertIcon(icon_10);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_11=(JButton)e.getSource();
                Icon icon_11=jButton_11.getIcon();
                textArea2.insertIcon(icon_11);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_12=(JButton)e.getSource();
                Icon icon_12=jButton_12.getIcon();
                textArea2.insertIcon(icon_12);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_13=(JButton)e.getSource();
                Icon icon_13=jButton_13.getIcon();
                textArea2.insertIcon(icon_13);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_14=(JButton)e.getSource();
                Icon icon_14=jButton_14.getIcon();
                textArea2.insertIcon(icon_14);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_15=(JButton)e.getSource();
                Icon icon_15=jButton_15.getIcon();
                textArea2.insertIcon(icon_15);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_16=(JButton)e.getSource();
                Icon icon_16=jButton_16.getIcon();
                textArea2.insertIcon(icon_16);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_17=(JButton)e.getSource();
                Icon icon_17=jButton_17.getIcon();
                textArea2.insertIcon(icon_17);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_18=(JButton)e.getSource();
                Icon icon_18=jButton_18.getIcon();
                textArea2.insertIcon(icon_18);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_19=(JButton)e.getSource();
                Icon icon_19=jButton_19.getIcon();
                textArea2.insertIcon(icon_19);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_20=(JButton)e.getSource();
                Icon icon_20=jButton_20.getIcon();
                textArea2.insertIcon(icon_20);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_21=(JButton)e.getSource();
                Icon icon_21=jButton_21.getIcon();
                textArea2.insertIcon(icon_21);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_22=(JButton)e.getSource();
                Icon icon_22=jButton_22.getIcon();
                textArea2.insertIcon(icon_22);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_23=(JButton)e.getSource();
                Icon icon_23=jButton_23.getIcon();
                textArea2.insertIcon(icon_23);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton_24=(JButton)e.getSource();
                Icon icon_24=jButton_24.getIcon();
                textArea2.insertIcon(icon_24);
                Expression_dialog.this.dispose();
                d.setVisible(false);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });

        /*button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });*/
    }
}
