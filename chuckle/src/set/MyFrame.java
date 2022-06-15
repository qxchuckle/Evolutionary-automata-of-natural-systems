package set;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{

    String[] str = new String[6];
    JTextField textField1 =new JTextField(10);
    JTextField textField2 =new JTextField(10);
    JTextField textField3 =new JTextField(10);
    JTextField textField4 =new JTextField(10);
    JTextField textField5 =new JTextField(10);
    JTextField textField6 =new JTextField(10);
    public  MyFrame(String title){
        super(title);
        JPanel root = new JPanel();
        this.setContentPane(root);
        JLabel label1= new JLabel("    狼：最大寿命");
        JLabel label2 = new JLabel("可育年龄");
        JLabel label3 = new JLabel("狐狸：最大寿命");
        JLabel label4 = new JLabel("可育年龄");
        JLabel label5 = new JLabel("兔子：最大寿命");
        JLabel label6 = new JLabel("可育年龄");
        JLabel label7= new JLabel("--------------------------");
        JLabel label8= new JLabel("--------------------------");
        root.add(label1);
        root.add(textField1);
        root.add(label2);
        root.add(textField2);
        root.add(label3);
        root.add(textField3);
        root.add(label4);
        root.add(textField4);
        root.add(label5);
        root.add(textField5);
        root.add(label6);
        root.add(textField6);
        root.add(label7);
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        JButton button1 = new JButton("确定");
        root.add(button1);
        JButton button2 = new JButton("清除");
        root.add(button2);
        root.add(label8);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str[0] = textField1.getText();
                str[1] = textField2.getText();
                str[2] = textField3.getText();
                str[3] = textField4.getText();
                str[4] = textField5.getText();
                str[5] = textField6.getText();
                System.out.println(getTxt1());
                dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2();
            }
        });
    }
    public String getTxt1(){
        return str[0];
    }
    public String getTxt2(){
        return str[1];
    }
    public String getTxt3(){
        return str[2];
    }
    public String getTxt4(){
        return str[3];
    }
    public String getTxt5(){
        return str[4];
    }
    public String getTxt6(){
        return str[5];
    }
    public  void text2(){
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }
}

