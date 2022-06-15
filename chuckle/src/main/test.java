package main;
import javax.swing.*;
import java.awt.*;

public class test {

    public static void main(String[] args) {

        JFrame jf=new JFrame("JTextArea的使用");// 创建一个标题为"JTextArea"的窗口
        jf.setBounds(100, 100, 800, 600);// 设置窗口的坐标和大小

        JPanel jp=new JPanel();// 创建一个面板
        JTextArea jta=new JTextArea("请输入内容", 20,20);// 创建一个指定文本,80行，100列的文本域
        jta.setLineWrap(true);// 设置一个自动换行
        jta.setForeground(Color.GRAY);// 设置组件的背景色
        jta.setFont(new Font("楷体",Font.BOLD,18));// 设置字体颜色
        jta.setBackground(Color.cyan);// 设置背景色
        JScrollPane jsp=new JScrollPane(jta);// 将文本域放在滚动条的面板上
        Dimension size=jta.getPreferredSize();// 获取文本域的首选大小
        jsp.setBounds(20,20,size.width,size.height);// 设置滚动面板的坐标和大小
        jp.add(jsp);// 将滚动面板添加到面板上
        jf.add(jp);// 将面板添加到窗口上


        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置窗口关闭即退出程序
        jf.setVisible(true);// 设置窗口可见
    }
}
