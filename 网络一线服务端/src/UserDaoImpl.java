import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String account,String password)
    {
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
                    User.setId(rs.getInt("id"));
                    User.setAccount(rs.getString("account"));
                    User.setUsername(rs.getString("username"));
                    User.setSex(rs.getString("sex"));
                    User.setAge(rs.getString("age"));
                    User.setMotto(rs.getString("motto"));
                    User.setPicture(rs.getString("picture"));
                    User.setBackground(rs.getString("background"));
                    User.setFriend(rs.getString("friend"));
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
            stm.setObject(2,User.getAccount());
            stm.setObject(3,User.getPassword());
            stm.setObject(4,User.getUsername());
            stm.setObject(5,User.getSex());
            stm.setObject(6,User.getAge());
            stm.setObject(7,User.getMotto());
            stm.setObject(8,User.getPicture());
            stm.setObject(9,User.getBackground());
            stm.setObject(10,User.getFriend());
            stm.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        conn.close();
    }

    @Override
    public Boolean save(User user) {
        boolean flag=false;
        DatabaseConnection conn=new DatabaseConnection();
        Connection hcon=conn.getConnection();
        String sql="update user set account=?,password=?,username=?,sex=?,age=?,motto=?,picture=?,background=?,friend=? where account = ?";
        try{
//            update 表名 set 列名1 = 值1, 列名2 = 值2,... [where 条件]
            PreparedStatement stmt=hcon.prepareStatement(sql);
            stmt.setString(1,User.getAccount());
            stmt.setString(2,User.getPassword());
            stmt.setString(3,User.getUsername());
            stmt.setString(4,User.getSex());
            stmt.setString(5,User.getAge());
            stmt.setString(6,User.getMotto());
            stmt.setString(7,User.getPicture());
            stmt.setString(8,User.getBackground());
            stmt.setString(9,User.getFriend());
            stmt.setString(10,User.getAccount());
            flag= (stmt.executeUpdate()==1);
            System.out.println(flag);
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
            stmt.setString(1,User.getAccount());
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                User.setId(rs.getInt("id"));
                User.setAccount(rs.getString("account"));
                User.setPassword(rs.getString("password"));
                User.setUsername(rs.getString("username"));
                User.setSex(rs.getString("sex"));
                User.setAge(rs.getString("age"));
                User.setMotto(rs.getString("motto"));
                User.setPicture(rs.getString("picture"));
                User.setBackground(rs.getString("background"));
                User.setFriend(rs.getString("friend"));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        conn.close();
        return user;
    }


}
