public interface UserDao {
    public abstract boolean login(String account,String password);
    public abstract void regist(User user);
    public abstract Boolean save(User user);
    public abstract User loadData(String account);
}
