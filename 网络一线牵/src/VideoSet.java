import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;

public class VideoSet extends Thread {
    Graphics g;
    private Socket client;
    private String serverIp;// 服务器IP
    private int port;// 服务器端口

    @Override
    public void run() {
        try {
            video();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public VideoSet(String serverIp,int port) {
        super();
        this.serverIp=serverIp;
        this.port=port;
    }

    public void video() throws IOException {
        JFrame jf = new JFrame();
        jf.setTitle("照镜子");
        jf.setSize(600, 490);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        g = jf.getGraphics();
        Webcam web = Webcam.getDefault();
        web.setViewSize(WebcamResolution.VGA.getSize());
        web.open();
        System.out.println("连接成功");
        client=new Socket(serverIp,port);
        while (true) {
            BufferedImage img = web.getImage();
            g.drawImage(img, 0, 0, null);
        }
    }

    // 接收对方的视频图像
    public void DrawCapture(byte[] buffer) {
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        try {
            BufferedImage show = ImageIO.read(bais);
            g.drawImage(show, 100, 100, null);
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }
}