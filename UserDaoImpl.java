import java.sql.*;

public class UserDaoImpl implements UserDao{
    @Override
    public boolean login(String account,String password)
    {
        boolean flag=false;
        DatabaseConnection conn=new DatabaseConnection();
        Connection hcon=conn.getConnection();
        try{
            String sql="select*from Affection";
            PreparedStatement stmt=hcon.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                if(account.equals(rs.getString("account"))&&password.equals(rs.getString("password")))
                {
                    flag=true;
                    User.setId(rs.getInt("id"));
                    User.setAccount(rs.getString("account"));
                    User.setUsername(rs.getString("name"));
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
        String sql_1="select count(*) from Affection";
        String sql_2="insert into Affection(id,account,password,username,sex,age,motto,picture,background,friend)value(?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stm=hcon.prepareStatement(sql_2);
            PreparedStatement id_stm=hcon.prepareStatement(sql_1);
            ResultSet rs=id_stm.executeQuery();
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
}
