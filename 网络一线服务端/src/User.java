public class User {
    public static int id;
    public static String password;
    public  static String account;
    public  static String username;
    public  static String sex;
    public  static String age;
    public  static String motto;
    public  static String picture;
    public  static String background;
    public  static String friend;
    public  static String uiconPath;

    public User()
    {

    }

    public static int getId()
    {
        return id;
    }
    public static String getPassword()
    {
        return password;
    }
    public static String getAccount()
    {
        return account;
    }
    public static String getUsername()
    {
        return username;
    }
    public static String getSex()
    {
        return sex;
    }
    public static String getAge()
    {
        return age;
    }
    public static String getMotto()
    {
        return motto;
    }
    public static String getPicture()
    {
        return picture;
    }
    public static String getBackground()
    {
        return background;
    }
    public static String getFriend()
    {
        return friend;
    }
    public static String getUiconPath() { return uiconPath; }

    public static void setId(int id)
    {
        User.id=id;
    }
    public static void setPassword(String password)
    {
        User.password=password;
    }
    public static void setAccount(String account)
    {
        User.account=account;
    }
    public static void setUsername(String username)
    {
        User.username=username;
    }
    public static void setSex(String sex)
    {
        User.sex=sex;
    }
    public static void setAge(String age)
    {
        User.age=age;
    }
    public static void setMotto(String motto)
    {
        User.motto=motto;
    }
    public static void setPicture(String picture)
    {
        User.picture=picture;
    }
    public static void setBackground(String background)
    {
        User.background=background;
    }
    public static void setFriend(String friend)
    {
        User.friend=friend;
    }
    public static void setUiconPath(String uiconPath) { User.uiconPath = uiconPath; }
}
