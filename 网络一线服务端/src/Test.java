public class Test {
    public static void main(String[] args){
        User user=new User();
//        User.setAccount("000");
//        User.setPassword("333");
//        User.setUsername("333");
//        User.setSex("333");
//        User.setAge("333");
//        User.setMotto("333");
//        User.setPicture("333");
//        User.setBackground("333");
//        User.setFriend("333");

        UserDaoImpl database=new UserDaoImpl();
//        database.regist(user);
//        database.save(user);
        User.setAccount("account3");
        user=database.loadData(User.getAccount());
        System.out.println(
           User.getId()+" "+
           User.getAccount()+" "+
           User.getPassword()+" "+
           User.getUsername()+" "+
           User.getSex()+" "+
           User.getAge()+" "+
           User.getMotto()+" "+
           User.getPicture()+" "+
           User.getPicture()+" "+
           User.getBackground()+" "
        );
    }
}
