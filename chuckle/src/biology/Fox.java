package biology;

import cell.Cell;
import java.awt.*;
import java.util.ArrayList;

//狐狸类
public class Fox extends Biology implements Cell {
    private int notEat = 0;
    private final double breedProbability = 0.24;//繁殖概率
    private final double eatProbability = 0.33;//觅食概率
    private final double eatLongerLife = 1.0;//增加的寿命
    private final double maxLife = 25.5;//增加后的最大寿命

    public Fox() {
        super(17.5, 4 , 12);
    }

    public Fox(double ageLimit, double reproductiveAge, double maxBreedAge) {
        super(ageLimit, reproductiveAge, maxBreedAge);
    }

    //繁殖
    @Override
    public Biology breed() {
        Biology ret = null;//声明一个可能出生的宝宝
        //判断是否能生育，且有一定概率才生
        if (isBreedAble() && Math.random() < breedProbability) {
            ret = new Fox();//把宝宝交给父类对象管理者管理
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
        if ((Math.random()-0.1*notEat) <  eatProbability) {
            ret = neighbour.get((int) (Math.random() * neighbour.size()));
            longerLife(eatLongerLife,maxLife);
            notEat = 0;
        }
        return ret;
    }

    @Override
    public void notEat(){
        notEat++;
        if(notEat >= 3){
            reduceLife(0.63);
        }
    }

    //用一串字符串表达这个生物的年龄和状态
    @Override
    public String toString() {
        return "狐狸:{" + super.toString() + " }";
    }
}
