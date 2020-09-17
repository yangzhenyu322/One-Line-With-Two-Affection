import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler extends Thread{

    Socket socket=new Socket();
    UserDaoImpl database=new UserDaoImpl();
    User user=new User();

    public ServerHandler(Socket socket){
        this.socket=socket;
    }

    //用于得到输出流，写数据
    public void out(String out) {
        try {
            socket.getOutputStream().write((out+"\n").getBytes("UTF-8"));
        } catch (IOException e) {
            ServerManager.getServetManager().remove(this);
            e.printStackTrace();
        };
    }

    @Override
    public void run() {
        DataInputStream in;
        DataOutputStream out;
        String flag;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            while (true){
                flag=in.readUTF();
                System.out.println("客户端请求："+flag);
                //服务端与数据库交互
                if("登陆".equals(flag.substring(0,2))){
                    login(flag,out);
                }else if("注册".equals(flag.substring(0,2))) {
                    registe(flag,out);
                }else if("保存".equals(flag.substring(0,2))){
                    System.out.println("已将数据保存");
                    out.writeUTF("保存成功");
                    save(flag,out);
                }else if("通讯".equals(flag.substring(0,2))){
                    chat(flag,out);

                }

//                if("end".equals(flag)){
//                    out.writeUTF("谢谢连接我：" + socket.getLocalSocketAddress() + "\nGoodbye!");
//                    System.out.println("--断开连接--");
//                    //结束连接
//                    in.close();
//                    out.close();
//                    socket.close();
//                    break;
//                }
                out.writeUTF("--已收到，请继续发送--");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chat(String flag, DataOutputStream out) {
        System.out.println(flag);
    }

    private void save(String flag, DataOutputStream out) {
        //保存数据
    }

    public void login(String flag,DataOutputStream out) throws IOException{
        System.out.println("正在尝试登陆...");
        //处理登陆界面的字符串
        String[] info=new String[2];
        for(int i=0;i<info.length;i++){
            info[i]="";
        }
        int index=0;
        for(int i=2;i<flag.length();i++){
            if(flag.charAt(i)!='#'){
                info[index]+=flag.charAt(i);
            }
            else {
                index++;
            }
        }
        User.setAccount(info[0]);
        User.setPassword(info[1]);

        if(database.login(user.getAccount(),user.getPassword())){
            System.out.println("成功登陆！");
            out.writeUTF("成功登陆！");
            user=database.loadData(User.getAccount());
            out.writeUTF(
                    "Usr: "+User.getId()+" "+
                            User.getAccount()+" "+
                            User.getPassword()+" "+
                            User.getUsername()+" "+
                            User.getSex()+" "+
                            User.getAge()+" "+
                            User.getMotto()+" "+
                            User.getPicture()+" "+
                            User.getPicture()+" "+
                            User.getBackground()+" "
            );
        }
        else {
            System.out.println("登陆失败！");
            out.writeUTF("登陆失败，请重新输入您的账号和密码！");
        }
    }

    public void registe(String flag,DataOutputStream out) throws IOException{
        User.setUsername("yzy");
        System.out.println("正在尝试注册...");
        //处理注册界面发送的请求字符串
        String[] info=new String[5];
        for(int i=0;i<info.length;i++){
            info[i]="";
        }
        int index=0;
        for(int i=2;i<flag.length();i++){
            if(flag.charAt(i)!='#'){
                info[index]+=flag.charAt(i);
            }
            else {
                index++;
            }
        }
        User.setAccount(info[0]);
        User.setPassword(info[1]);
        User.setUsername(info[2]);
        User.setSex(info[3]);
        User.setAge(info[4]);
        database.regist(user);
        System.out.println("成功注册！");
        out.writeUTF("已完成注册！");
    }


}
