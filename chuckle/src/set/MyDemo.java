package set;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDemo {
    static String[] str = new String[6];
    static JTextField textField1 =new JTextField(10);
    static JTextField textField2 =new JTextField(10);
    static JTextField textField3 =new JTextField(10);
    static JTextField textField4 =new JTextField(10);
    static JTextField textField5 =new JTextField(10);
    static JTextField textField6 =new JTextField(10);
    static JFrame frame = new JFrame("食物链系统");
    static JPanel root = new JPanel();
    static JLabel label1= new JLabel("    狼：最大寿命");
    static JLabel label2 = new JLabel("可育年龄");
    static JLabel label3 = new JLabel("狐狸：最大寿命");
    static JLabel label4 = new JLabel("可育年龄");
    static JLabel label5 = new JLabel("兔子：最大寿命");
    static JLabel label6 = new JLabel("可育年龄");
    static JLabel label7= new JLabel("--------------------------");
    static JLabel label8= new JLabel("--------------------------");
    static JButton button1 = new JButton("确定");
    static JButton button2 = new JButton("清除");

    public static String getTxt1(){
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
    public static void text2() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }

    public static void main(String[] args){
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));//左对齐
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点x直接关闭程序
        frame.setSize( 410, 150);
        frame.setLocationRelativeTo(null);//窗口居中
        frame.setVisible(true);//可视
        frame.setResizable(false);//不允许更改窗口大小
        frame.setContentPane(root);
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
        root.add(button1);
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
                frame.dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text2();
            }
        });
        System.out.println(textField1.getText());
    }
}
