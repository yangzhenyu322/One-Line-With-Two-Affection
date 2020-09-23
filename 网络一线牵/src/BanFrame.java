import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class BanFrame extends JFrame{

    JLabel label = new JLabel("请填写你需要封禁的账号：");

    JButton comfirm = new JButton("确定");

    JTextField accountText = new JTextField();

    public BanFrame(ServerFrame serverFrame)
    {
        BanFrame f = this;

        setSize(400, 200);

        setSize(400, 200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setLocation((width - 500)/2, (height - 200)/2);

        label.setBounds(100, 10, 200, 30);
        label.setFont(new Font("宋体", 0, 16));
        label.setForeground(Color.black);

        accountText.setBounds(50, 60, 300, 30);

        comfirm.setBounds(170, 110, 60, 30);

        ImageIcon background = new ImageIcon("./chatting_background\\background15.png");
        JLabel Background = new JLabel(background);
        Background.setBounds(0, 0 ,400, 200);
        add(Background);

        Background.add(label);
        Background.add(accountText);
        Background.add(comfirm);

        setVisible(true);

        comfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获得需要封禁的账号
                String account = accountText.getText();
                TransferInfo tif = new TransferInfo();
                tif.setAccount(account);
                tif.setReciver(account);
                tif.setStatusEnum(ChatStatus.BAN);
                tif.setNotice("由于存在违规行为，" + account + "已被封禁");
                Socket socket = ChatServer.userSocketMap.get(account);
                ServerHandler server = new ServerHandler(socket, serverFrame);

                //将被封禁的账号的聊天主窗口关闭
                server.TurnOff(tif);

                //将需要封禁的账号从列表中移除
                server.loginOut(tif);

                JOptionPane.showMessageDialog(f,account + "已被封禁！");
                f.setVisible(false);
            }
        });
    }
}
