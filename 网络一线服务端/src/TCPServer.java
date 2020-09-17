// 文件名 GreetingServer.java

import com.sun.source.tree.Scope;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPServer extends Thread
{
    int port;

    public TCPServer(int port) throws IOException
    {
        this.port=port;
    }

    @Override
    public void run()
    {
            try
            {
                //建立服务器的socket监听
                ServerSocket serverSocket=new ServerSocket(port);
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                while (true){
                    //进行数据交互
                    Socket socket=serverSocket.accept();
//                    ServerHandler serverHandler = new ServerHandler(socket , serverFrame);
                    ServerHandler serverHandler = new ServerHandler(socket);
                    serverHandler.start();
                    System.out.println("服务器接受到客户端的连接：" + socket);

//               让线程休眠1s->避免死机或荡机
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }

            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");

            }catch(IOException e)
            {
                e.printStackTrace();
            }
    }

    public static void main(String [] args)
    {
        try
        {
            new TCPServer(6060).start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}