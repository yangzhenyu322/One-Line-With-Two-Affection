import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class ByteObjectConvert {
    //对象转byte
    protected static byte[] objectToByte(Object obj)
    {
        byte[]bytes=null;
        try{
            ByteArrayOutputStream bo=new ByteArrayOutputStream();
            ObjectOutputStream oo=new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes=bo.toByteArray();
            bo.close();
            oo.close();
        }catch(Exception e)
        {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    //byte转对象
    protected static Object byteToObject(byte[] bytes)
    {
        Object obj = null;
        try{
            ByteArrayInputStream bi=new ByteArrayInputStream(bytes);
            ObjectInputStream oi=new ObjectInputStream(bi);
            obj=oi.readObject();
            bi.close();
            oi.close();
        }catch(Exception e)
        {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }
}
