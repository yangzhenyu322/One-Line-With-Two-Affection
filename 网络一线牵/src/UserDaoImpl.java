import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String account,String password)
    {
        User user=new User();
        boolean flag=false;
        DatabaseConnection conn=new DatabaseConnection();
        Connection hcon=conn.getConnection();
        try{
            String sql="select*from user";
            PreparedStatement stmt=hcon.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                if(account.equals(rs.getString("account"))&&password.equals(rs.getString("password")))
                {
                    flag=true;
                    user.setId(rs.getInt("id"));
                    user.setAccount(rs.getString("account"));
                    user.setUsername(rs.getString("username"));
                    user.setSex(rs.getString("sex"));
                    user.setAge(rs.getString("age"));
                    user.setMotto(rs.getString("motto"));
                    user.setPicture(rs.getString("picture"));
                    user.setBackground(rs.getString("background"));
                    user.setFriend(rs.getString("friend"));
                    break;
                }
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        conn.close();
        return flag;
    }

    @Override
    public void regist(User user)
    {
        DatabaseConnection conn=new DatabaseConnection();
        Connection hcon=conn.getConnection();
        String sql_1="select count(*) from user ";
        String sql_2="insert into user(id,account,password,username,sex,age,motto,picture,background,friend) value(?,?,?,?,?,?,?,?,?,?)";
//        insert into 表名(列名1,列名2,...列名n) values(值1,值2,...值n)
        try{
            PreparedStatement stm=hcon.prepareStatement(sql_2);
            PreparedStatement id_stm=hcon.prepareStatement(sql_1);
            ResultSet rs=id_stm.executeQuery();
            rs.next();
            int id_insert=rs.getInt(1)+1;
            stm.setObject(1,id_insert);
            stm.setObject(2,user.getAccount());
            stm.setObject(3,user.getPassword());
            stm.setObject(4,user.getUsername());
            stm.setObject(5,user.getSex());
            stm.setObject(6,user.getAge());
            stm.setObject(7,user.getMotto());
            stm.setObject(8,user.getPicture());
            stm.setObject(9,user.getBackground());
            stm.setObject(10,user.getFriend());
            stm.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        conn.close();
    }

    //保存用户修改的信息
    @Override
    public Boolean save(User user) {
        boolean flag=false;
        DatabaseConnection conn=new DatabaseConnection();
        Connection hcon=conn.getConnection();
        String sql="update user set username=?,sex=?,age=?,motto=?,picture=? where account = ?";
        try{
//            update 表名 set 列名1 = 值1, 列名2 = 值2,... [where 条件]
            PreparedStatement stmt=hcon.prepareStatement(sql);
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getSex());
            stmt.setString(3,user.getAge());
            stmt.setString(4,user.getMotto());
            stmt.setString(5,user.getPicture());
            stmt.setString(6,user.getAccount());
            flag= (stmt.executeUpdate()==1);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        conn.close();
        return flag;
    }

    //保存用户修改的头像
    public Boolean savePicture(User user) {
        boolean flag=false;
        DatabaseConnection conn=new DatabaseConnection();
        Connection hcon=conn.getConnection();
        String sql="update user set picture=? where account = ?";
        try{
//            update 表名 set 列名1 = 值1, 列名2 = 值2,... [where 条件]
            PreparedStatement stmt=hcon.prepareStatement(sql);
            stmt.setString(1,user.getPicture());
            stmt.setString(2,user.getAccount());
            flag= (stmt.executeUpdate()==1);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        conn.close();
        return flag;
    }

    @Override
    public User loadData(String account) {
        DatabaseConnection conn=new DatabaseConnection();
        Connection hcon=conn.getConnection();
        User user=new User();
        String sql="select * from user where account=?";

        try{
//            update 表名 set 列名1 = 值1, 列名2 = 值2,... [where 条件]
            PreparedStatement stmt=hcon.prepareStatement(sql);
            stmt.setString(1,account);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setAccount(rs.getString("account"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setSex(rs.getString("sex"));
                user.setAge(rs.getString("age"));
                user.setMotto(rs.getString("motto"));
                user.setPicture(rs.getString("picture"));
                user.setBackground(rs.getString("background"));
                user.setFriend(rs.getString("friend"));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        conn.close();
        return user;
    }

}
