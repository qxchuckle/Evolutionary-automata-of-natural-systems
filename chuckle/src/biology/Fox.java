package biology;

import cell.Cell;
import java.awt.*;
import java.util.ArrayList;

//狐狸类
public class Fox extends Biology implements Cell {

    public Fox() {
        super(18, 4);
    }

    public Fox(int ageLimit, int reproductiveAge) {
        super(ageLimit, reproductiveAge);
    }

    //繁殖
    @Override
    public Biology breed() {
        Biology ret = null;//声明一个可能出生的宝宝
        //判断是否能生育，且有一定概率才生
        if (isBreedable() && Math.random() < 0.16) {
            ret = new Fox(super.getAgeLimit(), super.getReproductiveAge());//把宝宝交给父类对象管理者管理
        }
        return ret;//返回这个宝宝
    }

    //画自己
    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(new Color(255, 0, 0, colorTransparency()));//设置格子颜色
        g.fillRect(x, y, size, size);//根据上面设的颜色画个实心矩形
    }

    public Biology feed(ArrayList<Biology> neighbour) {
        Biology ret = null;
        if (Math.random() < 0.2) {
            ret = neighbour.get((int) (Math.random() * neighbour.size()));
            longerLife(2);
        }
        return ret;
    }

    //用一串字符串表达这个生物的年龄和状态
    @Override
    public String toString() {
        return "狐狸:{" + super.toString() + " }";
    }
}
