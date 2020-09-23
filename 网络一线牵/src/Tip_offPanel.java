import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tip_offPanel {

    //举报信息区域
    public JTextPane Tip_offText;

    //忽略按钮和封号按钮
    JButton ignore = new JButton("清空");
    JButton ban = new JButton("封号");

    public JLabel getTip_offPanel(ServerFrame serverFrame){
        //举报信息面板
        JPanel Tip_off = new JPanel();
        Tip_off.setLayout(null);
        Tip_off.setBounds(0, 0, ServerFrame.FRAME_WIDTH, ServerFrame.FRAME_HEIGHT);
        Tip_off.setOpaque(false);//设置透明

        //举报信息标签
        JLabel lblLog = new JLabel("[举报信息]");
        lblLog.setForeground(Color.BLACK);
        lblLog.setFont(new Font("宋体", 0, 16));
        lblLog.setBounds(100, 5, 100, 30);
        Tip_off.add(lblLog);

        //举报信息区域
        Tip_offText = new JTextPane();
        Tip_offText.setOpaque(false);
        Tip_offText.setFont(new Font("宋体", 0, 18));
        Tip_offText.setEditable(false);
        Tip_offText.setText(" ");

        JScrollPane scoPaneOne = new JScrollPane(Tip_offText);// 设置滚动条
        scoPaneOne.setBounds(100, 35, 340, 300);
        scoPaneOne.setOpaque(false);
        scoPaneOne.getViewport().setOpaque(false);

        Tip_off.add(scoPaneOne);

        //忽略和封号按钮
        ignore.setBounds(125, 350, 60, 30);
        ban.setBounds(365, 350, 60, 30);
        Tip_off.add(ignore);
        Tip_off.add(ban);

        ignore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tip_offText.setText("");
            }
        });

        //给举报信息界面添加背景图片
        ImageIcon backgroundImage = new ImageIcon("./chatting_background\\background13.jpg");
        //创建一个标签并将图片添加进去
        JLabel background = new JLabel(backgroundImage);
        //设置图片的位置和大小
        background.setBounds(0, 0, ServerFrame.FRAME_WIDTH, ServerFrame.FRAME_HEIGHT);
        //添加到当前窗体中
        background.add(Tip_off);

        ban.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BanFrame banFrame = new BanFrame(serverFrame);
            }
        });

        return background;
    }
}
