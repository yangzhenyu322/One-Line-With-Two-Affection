public interface UserDao {
    public abstract boolean login(String account,String password);
    public abstract void regist(User user);
}
