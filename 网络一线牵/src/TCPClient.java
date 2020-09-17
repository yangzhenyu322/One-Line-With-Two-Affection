// 文件名 GreetingClient.java
//输入"end"与服务端断开连接

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient extends Thread
{
    Socket client;
    public DataInputStream in;
    public DataOutputStream out;
    public User user;
    LoginFrame loginFrame;


    public TCPClient(Socket socket,LoginFrame loginFrame) throws IOException{
        this.client=socket;
        this.loginFrame=loginFrame;
        System.out.println("连接到主机：" + client.getLocalAddress() + " ，端口号：" + client.getLocalPort());
        System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
        System.out.println("--请向服务端发送请求--");
        //数据流
        InputStream inFromServer = client.getInputStream();
        in = new DataInputStream(inFromServer);

        OutputStream outToServer = client.getOutputStream();
        out = new DataOutputStream(outToServer);

        //用户
        user = new User();
    }

    @Override
    public void run(){
        while (true){
            try {
                if(loginFrame.statu.length()>=2&&"登陆".equals(loginFrame.statu.substring(0,2))){
                    out.writeUTF(
                            loginFrame.statu
                    );
                    System.out.println(in.readUTF());
                    System.out.println(in.readUTF());
                    System.out.println(in.readUTF());
                    loginFrame.statu="";
                }

                if(loginFrame.statu.length()>=2&&"注册".equals(loginFrame.statu.substring(0,2))){
                    out.writeUTF(
                            loginFrame.statu
                    );
                    System.out.println(in.readUTF());
                    System.out.println(in.readUTF());
                    loginFrame.statu="";
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }catch (IOException e) {
                e.printStackTrace();
            }

//            Scanner input=new Scanner(System.in);
//            String inputStr=input.next();
//            try {
//                out.writeUTF(inputStr);
//                System.out.println("服务器响应： " + in.readUTF());
//                if("end".equals(inputStr)) {
//                    System.out.println("--与服务端断开连接--");
//                    in.close();
//                    out.close();
//                    client.close();
//                }
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

}