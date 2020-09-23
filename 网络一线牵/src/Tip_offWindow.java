import javax.swing.*;
import java.net.Socket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tip_offWindow extends JFrame{

    ImageIcon background = new ImageIcon("./chatting_background\\background11.jpg");
    JLabel Background = new JLabel(background);

    /**
     * 登录窗体宽度
     */
    private static final Integer FRAME_WIDTH = 500;

    /**
     * 登录窗体高度
     */
    private static final Integer FRAME_HEIGHT = 300;

    JLabel account = new JLabel("举报账户：");

    JLabel reason = new JLabel("举报原因：");
    JLabel otherReason = new JLabel("其他原因：");

    //JButton file = new JButton("选择图片");
    JButton send = new JButton("提交");

    JTextField accountName = new JTextField();
    JComboBox reasonText;
    JTextField otherReasonText = new JTextField();

    public Tip_offWindow(String Account, Socket socket, String sender){

        Tip_offWindow f = this;

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setLocation((width - FRAME_WIDTH)/2, (height - FRAME_HEIGHT)/2);

        Background.setBounds(0, 0, 500, 300);

        account.setBounds(20, 20, 80, 50);
        account.setFont(new Font("宋体", 0, 15));
        account.setForeground(Color.red);
        accountName.setBounds(110, 30, 350, 30);
        accountName.setFont(new Font("宋体", 0, 15));
        accountName.setText(Account);
        accountName.setEditable(false);

        reason.setBounds(20, 90, 80, 50);
        reason.setFont(new Font("宋体", 0, 15));
        reason.setForeground(Color.yellow);
        String[] Reason = new String[]{"该账号发布色情/违法等不良信息", "该账号存在欺诈骗钱行为",
                "该账号提供考试舞弊服务", "侵犯未成年人权益" ,"该账号存在其他违规行为"};
        reasonText = new JComboBox(Reason);
        reasonText.setBounds(110, 100, 350, 30);
        reasonText.setFont(new Font("宋体", 0, 15));

        otherReason.setBounds(20, 160, 80, 50);
        otherReason.setFont(new Font("宋体", 0, 15));
        otherReason.setForeground(Color.pink);
        otherReasonText.setBounds(110, 170, 350, 30);
        otherReasonText.setFont(new Font("宋体", 0, 15));
        otherReasonText.setText("无");

        //file.setBounds(120, 220, 90, 30);
        send.setBounds(220, 220, 60, 30);

        add(Background);

        Background.add(account);
        Background.add(accountName);
        Background.add(reason);
        Background.add(reasonText);
        Background.add(otherReason);
        Background.add(otherReasonText);
        //Background.add(file);
        Background.add(send);

        setResizable(false);

        setVisible(true);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferInfo tif = new TransferInfo();
                //举报原因
                String reason;
                reason = reasonText.getSelectedItem() + "\n其它原因：" + otherReasonText.getText();
                tif.setTip_offInformation(reason);
                //发送人
                tif.setSender(sender);
                String reciver = "All";
                //我们要获取到，当前要发送给谁？
                reciver = Account;
                //接收人
                tif.setReciver(reciver);
                //本次处理的消息类型为举报信息
                tif.setStatusEnum(ChatStatus.TIP_OFF);
                IOStream.writeMessage(socket, tif);

                JOptionPane.showMessageDialog(f,"提交成功！");
                f.setVisible(false);
            }
        });
    }

}
