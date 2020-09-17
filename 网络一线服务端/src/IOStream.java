import java.io.*;
import java.net.Socket;
public class IOStream {
    public static Object readMessage(Socket socket)
    {
        Object obj=null;
        try{
            InputStream is=socket.getInputStream();
            DataInputStream dis=new DataInputStream(is);
            int objByteLen=dis.readInt();
            byte[]objByte=new byte[objByteLen];
            dis.readFully(objByte);
            obj=ByteObjectConvert.byteToObject(objByte);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return obj;
    }
    public static void writeMessage(Socket socket,Object obj)
    {
        try{
            OutputStream os=socket.getOutputStream();
            byte[]objByte=ByteObjectConvert.objectToByte(obj);
            DataOutputStream dos=new DataOutputStream(os);
            dos.writeInt(objByte.length);
            dos.write(objByte);
            dos.flush();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
