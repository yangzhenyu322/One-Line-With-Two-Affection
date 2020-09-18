import javax.swing.*;
import java.awt.*;

public class personal_information {

    JFrame f2 = new JFrame("个人资料");

    //昵称
    ImageIcon nameImage = new ImageIcon("./wordart\\word7.png");
    JLabel name = new JLabel(nameImage);
    JTextField nameText = new JTextField();

    //账号
    ImageIcon accountImage = new ImageIcon("./wordart\\word9.png");
    JLabel account = new JLabel(accountImage);
    JLabel accountText = new JLabel();

    //性别
    ImageIcon sexImage = new ImageIcon("./wordart\\word5.png");
    JLabel sex = new JLabel(sexImage);
    JTextField sexText = new JTextField();

    //年龄
    ImageIcon ageImage = new ImageIcon("./wordart\\word11.png");
    JLabel age = new JLabel(ageImage);
    JTextField ageText = new JTextField();

    //个性签名
    ImageIcon personalImage = new ImageIcon("./wordart\\word13.png");
    JLabel personal_show = new JLabel(personalImage);
    JTextField personalText = new JTextField();

    //等级
    ImageIcon gradeImage = new ImageIcon("./wordart\\word15.png");
    JLabel grade = new JLabel(gradeImage);
    JLabel gradePicture = new JLabel();

    JButton headPicture = new JButton();

    //编辑个人信息和确认按钮
    JButton edit = new JButton("编辑");
    JButton save = new JButton("确认");

    public personal_information()
    {
        f2.setLocation(600, 200);
        f2.setSize(400, 580);
        f2.setLayout(null);

        //创建一个JLabel用于储存背景图片
        ImageIcon image2;
        image2 = new ImageIcon("./chatting_background\\background7.jpg");
        JLabel jl2 = new JLabel(image2);
        jl2.setBounds(0, 0, 400, 560);

        name.setBounds(20, 170, 66, 30);
        nameText.setBounds(90, 170, 250, 30);

        account.setBounds(15, 220, 74, 30);
        accountText.setBounds(90, 220, 100, 30);

        sex.setBounds(20, 270, 64, 30);
        sexText.setBounds(90, 270, 250, 30);

        age.setBounds(20, 320, 68, 30);
        ageText.setBounds(90, 320, 250, 30);

        personal_show.setBounds(10, 370, 80, 30);
        personalText.setBounds(100, 370, 250, 30);

        grade.setBounds(20, 420, 64, 30);
        gradePicture.setBounds(90, 420, 100, 30);

        headPicture.setBounds(125, 10, 150, 150);

        edit.setBounds(170, 470, 60, 30);
        save.setBounds(170, 505, 60, 30);

        f2.add(jl2);

        jl2.add(name);
        jl2.add(nameText);
        jl2.add(account);
        jl2.add(accountText);
        jl2.add(sex);
        jl2.add(sexText);
        jl2.add(age);
        jl2.add(ageText);
        jl2.add(personal_show);
        jl2.add(personalText);
        jl2.add(grade);
        jl2.add(gradePicture);
        jl2.add(headPicture);
        jl2.add(edit);
        jl2.add(save);

        f2.setResizable(false);

        f2.setVisible(true);
    }
}
