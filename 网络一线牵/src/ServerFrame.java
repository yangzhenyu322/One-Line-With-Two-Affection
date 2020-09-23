import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;


/**
 * 服务器端主界面
 */
public class ServerFrame extends JFrame {

	private static final long serialVersionUID = -8152378291726535742L;

	public static final Integer FRAME_WIDTH = 550;
	public static final Integer FRAME_HEIGHT = 500;
		
	//用户列表界面
	public OnlineUserPanel onlineUserPanel;
	
	//服务器参数选项卡
	public ServerInfoPanel serverInfoPanel;

	//举报信息选项卡
	public Tip_offPanel tip_offPanel;

	public ServerFrame() {

		this.setTitle("网络聊天室服务器");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//窗体不可扩大
		setResizable(false);
		//获取屏幕
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		//屏幕居中处理
		setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);
		
		//设置窗体关闭，程序退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//选项卡
		JTabbedPane tpServer = new JTabbedPane(JTabbedPane.TOP);
		tpServer.setBackground(Color.WHITE);
		tpServer.setFont(new Font("宋体", 0, 18));
		tpServer.setUI(new CustomTabbedPaneUI());

		tip_offPanel = new Tip_offPanel();
		serverInfoPanel = new ServerInfoPanel();
		onlineUserPanel = new OnlineUserPanel();
		JLabel serverInfoPanel_label=serverInfoPanel.getServerInfoPanel();
		JLabel onlineUserPanel_label=onlineUserPanel.getUserPanel();
		tpServer.add("服务器信息" , serverInfoPanel_label);
		tpServer.add("举报信息" , tip_offPanel.getTip_offPanel(this));
		add(tpServer);
		setVisible(true);
	}
	
}
