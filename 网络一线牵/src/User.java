/**
 * 好友列表展示
 * @author Ron
 *
 */

public class User {
	private  int id;
	private   String account;
	private  String password;
	private   String username;
	private   String sex;
	private   String age;
	private   String motto;
	private   String picture;
	private   String background;
	private   String friend;


	public  int getId()
	{
		return id;
	}
	public  String getPassword()
	{
		return password;
	}
	public  String getAccount()
	{
		return account;
	}
	public  String getUsername()
	{
		return username;
	}
	public  String getSex()
	{
		return sex;
	}
	public  String getAge()
	{
		return age;
	}
	public  String getMotto()
	{
		return motto;
	}
	public  String getPicture()
	{
		return picture;
	}
	public  String getBackground()
	{
		return background;
	}
	public  String getFriend()
	{
		return friend;
	}

	public  void setId(int id)
	{
		this.id=id;
	}
	public  void setPassword(String password)
	{
		this.password=password;
	}
	public  void setAccount(String account)
	{
		this.account=account;
	}
	public  void setUsername(String username)
	{
		this.username=username;
	}
	public  void setSex(String sex)
	{
		this.sex=sex;
	}
	public  void setAge(String age)
	{
		this.age=age;
	}
	public  void setMotto(String motto)
	{
		this.motto=motto;
	}
	public  void setPicture(String picture)
	{
		this.picture=picture;
	}
	public  void setBackground(String background)
	{
		this.background=background;
	}
	public  void setFriend(String friend)
	{
		this.friend=friend;
	}
}
