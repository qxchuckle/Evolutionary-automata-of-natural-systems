package biology;

import cell.Cell;
import java.awt.*;

//兔子类
public class Rabbit extends Biology implements Cell {
    private final double breedProbability = 0.38;//繁殖概率

    public Rabbit() {
        super(10.5, 1.5, 8);
    }

    public Rabbit(double ageLimit, double reproductiveAge, double maxBreedAge) {
        super(ageLimit, reproductiveAge, maxBreedAge);
    }

    @Override
    public Biology breed() {
        Biology ret = null;//声明一个可能出生的宝宝
        //判断是否能生育，且有一定概率才生
        if (isBreedAble() && Math.random() < breedProbability) {
            ret = new Rabbit();//把宝宝交给父类对象管理者管理
        }
        return ret;//返回这个宝宝
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(new Color(0, 0, 0, colorTransparency()));//设置格子颜色
        g.fillRect(x, y, size, size);//根据上面设的颜色画个实心矩形
    }

    //用一串字符串表达这个生物的年龄和状态
    @Override
    public String toString() {
        return "兔子:{" + super.toString() + " }";
    }
}
