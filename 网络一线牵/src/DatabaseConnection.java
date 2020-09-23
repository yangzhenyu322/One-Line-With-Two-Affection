import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DBDRIVER="com.mysql.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/Affection?useUnicode=true&characterEncoding=utf8";
    private static final String DBUSER="root";
    private static final String PASSWORD="root";
    private Connection conn=null;
    public DatabaseConnection()
    {
        try{
            Class.forName(DBDRIVER);
            this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return this.conn;
    }
    public void close()
    {
        if(this.conn!=null)
        {
            try{
                this.conn.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
