package main;

import biology.Biology;
import biology.Fox;
import biology.Rabbit;
import biology.Wolf;
import cell.Cell;
import field.Coordinate;
import field.Field;
import field.Statistic;
import field.View;
import set.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
这是一个可扩展性较强的演化系统，
实现了MVC设计模式，既数据、表现和控制三者分离，各负其责

如果想在这个系统中加入新的生物，仅需两步
1、新建一个生物类继承自Biology父类，实现Cell接口
2、修改Main类中的
    构造方法（让网格中有这个生物）、
    修改step()单步方法中的“吃”（吃什么由系统决定，生物只需管好自己，知道自己能吃就行）、
    修改initialization()、statistic()、putTxt()三个统计相关的方法
*/
public class Main {

    private Field theField;
    private View theView;
    private int n = 0;
    private static Thread t;
    //统计对象
    private Statistic foxStt = new Statistic();
    private Statistic rabbitStt = new Statistic();
    private Statistic wolfStt = new Statistic();
    //组件
    private JTextArea txt=new JTextArea("刷新：刷新网格，重新投放生物\r\n开始：开始演化\r\n暂停：暂停演化\r\n\n");
    private JScrollPane jsp=new JScrollPane(txt);
    private JPanel jPanel = new JPanel(new BorderLayout());
    private JFrame frame = new JFrame();
    private static MyFrame frame2 = new MyFrame("食物链系统数据设置");
    //监听器
    private class btnListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            txt.append("\r\n程序运行中~~~\r\n\n");
            t.resume();
        }
    }
    private class btnListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            txt.append("\r\n程序暂停运行了，点开始继续运行~~~~\r\n\n");
            t.suspend();
        }
    }
    private class btnListener3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            t.suspend();
            frame.setVisible(false);
            newThread();
        }
    }

    //构造方法
    public Main(int size) {
        theField = new Field(size, size);
        for (int row = 0; row < theField.getHeight(); row++) {
            for (int col = 0; col < theField.getWidth(); col++) {
                double probability = Math.random();
                if (probability < 0.05) {
                    theField.place(row, col, new Wolf());
                } else if (probability < 0.1) {
                    theField.place(row, col, new Fox());
                } else if (probability < 0.2) {
                    theField.place(row, col, new Rabbit());
                }
            }
        }
        GUI();
    }

    //创建一个线程
    public static void newThread() {
        Main bly = new Main(90);//设置网格90*90
        t = new Thread(() -> {
            while (true) {
                bly.start();
            }
        });
        t.start();
        t.suspend();//因为在这个程序中并不会造成死锁，所以用这个方法挂起线程简单方便
    }

    //生成演化系统GUI
    public void GUI(){
        //组件
        theView = new View(theField);
        txt.setColumns(31);
        txt.setLineWrap(true);
        txt.setBackground(Color.gray);
        txt.setEnabled(false);
        txt.setFont(new java.awt.Font("", 0, 16));
        JButton btn1 =new JButton("开始");
        btn1.addActionListener(new btnListener1());
        JButton btn2 =new JButton("暂停");
        btn2.addActionListener(new btnListener2());
        JButton btn3 =new JButton("重新开始");
        btn3.addActionListener(new btnListener3());
        jPanel.add(btn1,BorderLayout.WEST);
        jPanel.add(btn2,BorderLayout.EAST);
        jPanel.add(btn3,BorderLayout.NORTH);
        //底层界面
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("食物链演化系统: [ 兔子：黑色 ] [ 狐狸：红色 ] [ 狼：蓝色 ]");
        frame.add(theView);
        frame.add(jsp, BorderLayout.WEST);
        frame.add(jPanel, BorderLayout.EAST);
        frame.pack();//根据窗口里面的布局及组件的preferredSize来确定frame的最佳大小
        frame.setVisible(true);
    }

    //启动演化
    public void start() {
            step();//执行一次演化
            theView.repaint();//演化完就重新画整个画面
            //聚焦文本域到末尾
            txt.selectAll();
            if (txt.getSelectedText() != null) {
                txt.setCaretPosition(txt.getSelectedText().length());
                txt.requestFocus();
            }
            //延迟运行
            try {
                Thread.sleep(300);//延迟300ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    //系统演化单步
    public void step() {
        initialization();//统计初始化
        n++;//统计轮数
        for (int row = 0; row < theField.getHeight(); row++) {
            for (int col = 0; col < theField.getWidth(); col++) {
                Cell cell = theField.getBiology(row, col);
                if (cell != null) {

                    Biology biology = (Biology) cell;//方便操作
                    //System.out.println(biology.toString());//输出每个动物的信息

                    statistic(biology);//统计

                    //生物的动作
                    if (biology.isAlive()) {
                        biology.grow();//长大
                        Coordinate loc = biology.move(theField.getFreeNeighbour(row, col));
                        if (loc != null) {
                            theField.move(row, col, loc);
                        }

                        //吃
                        Cell[] neighbour = theField.getNeighbour(row, col);
                        ArrayList<Biology> listFood = new ArrayList<Biology>();
                        for (Cell bl : neighbour) {
                            if (cell instanceof Fox) {//狐狸吃的
                                if (bl instanceof Rabbit) {
                                    listFood.add((Rabbit) bl);
                                }
                            }
                            if (cell instanceof Wolf) {//狼吃的
                                if (bl instanceof Rabbit) {
                                    listFood.add((Rabbit) bl);
                                }
                                if (bl instanceof Fox) {
                                    listFood.add((Fox) bl);
                                }
                            }
                        }
                        if (!listFood.isEmpty()) {
                                Biology fed = biology.feed(listFood);
                                if (fed != null) {
                                    theField.remove(row, col, (Cell) fed);
                                }
                        }

                        //繁衍
                        Biology baby = biology.breed();
                        if (baby != null) {
                            theField.placeBaby(row, col, (Cell) baby);
                        }

                    } else {
                        theField.remove(row, col);
                    }
                }
            }
        }

        putTxt();//在左侧面板输出文本
    }

    //统计初始化
    private void initialization(){

        foxStt.initialization();
        rabbitStt.initialization();
        wolfStt.initialization();
    }

    //统计
    private void statistic(Biology biology){
        if(biology instanceof Fox){
            foxStt.increaseNumber();
            foxStt.setTotalAge(biology.getAge()+foxStt.getTotalAge());
        }else if(biology instanceof Rabbit){
            rabbitStt.increaseNumber();
            rabbitStt.setTotalAge(biology.getAge()+rabbitStt.getTotalAge());
        }else if(biology instanceof Wolf){
            wolfStt.increaseNumber();
            wolfStt.setTotalAge(biology.getAge()+wolfStt.getTotalAge());
        }
    }

    //在左侧面板输出信息
    private void putTxt(){
        String str1 = "《第" + n + "轮》：";
        String str2 = "生物总数:[ " + (foxStt.getNumber()+rabbitStt.getNumber()+wolfStt.getNumber()) + " ]  " +
                "狐狸:[ " + foxStt.getNumber() + " ]  " +
                "兔子:[ " + rabbitStt.getNumber() + " ]  " +
                "狼:[ " + wolfStt.getNumber() + " ]";
        String str3 = "平均年龄:狐狸:[ "+ String.format("%.1f",foxStt.averageAge())+" ]  " +
                "兔子:[ "+ String.format("%.1f",rabbitStt.averageAge())+" ]  " +
                "狼:["+ String.format("%.1f",wolfStt.averageAge())+" ]\n";
        //向文本域中文本后面加内容
        txt.append(str1+"\r\n"+str2+"\r\n"+ str3+"-----------------------------------------------------------------------------\r\n");
    }

    public static void main(String[] args) {

        newThread();
    }
}
