import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class personal_information {

    JFrame f2 = new JFrame("个人资料");

    //昵称
    ImageIcon nameImage = new ImageIcon("./wordart\\word7.png");
    JLabel name = new JLabel(nameImage);
    JTextField nameText;

    //账号
    ImageIcon accountImage = new ImageIcon("./wordart\\word9.png");
    JLabel account_Label = new JLabel(accountImage);
    JLabel accountText;

    //性别
    ImageIcon sexImage = new ImageIcon("./wordart\\word5.png");
    String[] man = new String[]{"男","女"};
    JLabel sex = new JLabel(sexImage);
    JComboBox sexText = new JComboBox(man);

    //年龄
    ImageIcon ageImage = new ImageIcon("./wordart\\word11.png");
    JLabel age = new JLabel(ageImage);
    JTextField ageText;

    //个性签名
    ImageIcon personalImage = new ImageIcon("./wordart\\word13.png");
    JLabel personal_show = new JLabel(personalImage);
    JTextField personalText;

    //等级
    ImageIcon gradeImage = new ImageIcon("./wordart\\word15.png");
    ImageIcon grade1 = new ImageIcon("./src\\image\\grade1.png");
    ImageIcon grade2 = new ImageIcon("./src\\image\\grade1.png");
    ImageIcon grade3 = new ImageIcon("./src\\image\\grade1.png");
    JLabel grade = new JLabel(gradeImage);
    JLabel gradePicture1 = new JLabel(grade1);
    JLabel gradePicture2 = new JLabel(grade1);
    JLabel gradePicture3 = new JLabel(grade1);


    //头像
    public static ImageIcon headImage;
    public static JButton headPicture;

    //编辑个人信息和确认按钮
    JButton edit = new JButton("编辑");
    JButton save = new JButton("保存");

    public personal_information(String account, Socket socket)
    {
        User user=new UserDaoImpl().loadData(account);
        nameText = new JTextField(user.getUsername());
        accountText = new JLabel(user.getAccount());
        sexText.setSelectedItem(user.getSex());
        ageText = new JTextField(user.getAge());
        personalText = new JTextField(user.getMotto());

        //设置头像
        headImage = new ImageIcon("./head_portrait\\head"+new UserDaoImpl().loadData(account).getPicture()+"(4).png");
        headPicture = new JButton(headImage);

        f2.setLocation(600, 200);
        f2.setSize(400, 580);
        f2.setLayout(null);

        //创建一个JLabel用于储存背景图片
        ImageIcon image2;
        image2 = new ImageIcon("./chatting_background/background7.jpg");
        JLabel jl2 = new JLabel(image2);
        jl2.setBounds(0, 0, 400, 560);

        name.setBounds(20, 170, 66, 30);
        nameText.setBounds(90, 170, 250, 30);
        nameText.setEditable(false);

        account_Label.setBounds(15, 220, 74, 30);
        accountText.setBounds(90, 220, 100, 30);
        accountText.setText(account);

        sex.setBounds(20, 270, 64, 30);
        sexText.setBounds(90, 270, 250, 30);
        sexText.setEditable(false);
        sexText.setEnabled(false);

        age.setBounds(20, 320, 68, 30);
        ageText.setBounds(90, 320, 250, 30);
        ageText.setEditable(false);
        personal_show.setBounds(10, 370, 80, 30);
        personalText.setBounds(100, 370, 250, 30);
        personalText.setEditable(false);
        grade.setBounds(20, 420, 64, 30);
        gradePicture1.setBounds(90, 420, 48, 30);
        gradePicture2.setBounds(138, 420, 48, 30);
        gradePicture3.setBounds(184, 420, 48, 30);

        headPicture.setBounds(125, 10, 150, 150);
        headPicture.setContentAreaFilled(false);

        edit.setBounds(170, 470, 60, 30);
        save.setBounds(170, 505, 60, 30);

        f2.add(jl2);

        jl2.add(name);
        jl2.add(nameText);
        jl2.add(account_Label);
        jl2.add(accountText);
        jl2.add(sex);
        jl2.add(sexText);
        jl2.add(age);
        jl2.add(ageText);
        jl2.add(personal_show);
        jl2.add(personalText);
        jl2.add(grade);
        jl2.add(gradePicture1);
        jl2.add(gradePicture2);
        jl2.add(gradePicture3);
        jl2.add(headPicture);
        jl2.add(edit);
        jl2.add(save);

        f2.setResizable(false);

        f2.setVisible(true);

        headPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Head_choose hc = new Head_choose(account,socket);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setEditable(true);
                ageText.setEditable(true);
                personalText.setEditable(true);
                sexText.setEnabled(true);
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setEditable(false);
                ageText.setEditable(false);
                personalText.setEditable(false);
                sexText.setEnabled(false);
                //将数据保存到数据库中
                User user=new User();
                user.setAccount(account);
                user.setUsername(nameText.getText());
                user.setSex((String) sexText.getSelectedItem());
                user.setAge(ageText.getText());
                user.setMotto(personalText.getText());
                user.setPicture(new UserDaoImpl().loadData(account).getPicture());
                new UserDaoImpl().save(user);
            }
        });
    }
}


