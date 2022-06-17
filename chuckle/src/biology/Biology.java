package biology;

import field.Coordinate;
import java.util.ArrayList;

//生物类
public abstract class Biology {
    private double ageLimit;//默认最大年龄，超出这个年龄生物就会死亡
    private double reproductiveAge;//可生育年龄
    private double maxBreedAge;//最大可生育年龄
    private final boolean gender;//生物性别，false男true女
    private double age;//生物的当期年龄
    private boolean isAlive = true;//生物的死活，默认为活（刚new出来的当然是活的）

    //设置这个生物的最大年龄、可生育年龄和性别
    public Biology(double ageLimit, double reproductiveAge, double maxBreedAge) {
        this.ageLimit = ageLimit;
        this.reproductiveAge = reproductiveAge;
        this.maxBreedAge = maxBreedAge;
        this.gender = Math.random() < 0.5;
    }

    public double getAgeLimit() {
        return ageLimit;
    }
    public double getReproductiveAge() {
        return reproductiveAge;
    }
    public void setAgeLimit(double ageLimit) {
        this.ageLimit = ageLimit;
    }
    public void setReproductiveAge(double reproductiveAge) {
        this.reproductiveAge = reproductiveAge;
    }

    //获得当期生物的年龄
    public double getAge() {
        return age;
    }

    //让生物死亡
    public void die(){
        this.isAlive = false;
    }

    //获得生物状态
    public boolean isAlive() {
        return isAlive;
    }

    //让生物年龄增加0.7,如果年龄超过最大年龄，就死亡
    public void grow(){
        age += 1.0;
        if (age >= ageLimit) {
            die();
        }
    }

    //生育动作，至于怎么生育，交给子类去做
    public abstract Biology breed();

    //判断生物是否可生育
    public boolean isBreedAble() {
        return (gender && age >= reproductiveAge && age <= maxBreedAge);
    }

    //根据这个生物的年龄占整个生命进度的百分比确定颜色透明度
    public int colorTransparency(){
        double proportion = 1 - age/ageLimit;
        if(proportion < 0){//确保不会小于0
            proportion = 0;
        }
        return (int) ((proportion) * 255);
    }

    //提高年龄上限
    public void longerLife(double time,double limit) {
        if(ageLimit < limit) {
            ageLimit += time;
        }
    }
    //降低年龄上限
    public void reduceLife(double time) {
            ageLimit -= time;
    }

    //向周围的空位移动
    public Coordinate move(Coordinate[] freeAdj) {
        Coordinate ret = null;
        if (freeAdj.length > 0 && Math.random() < 0.03) {
            ret = freeAdj[(int) (Math.random() * freeAdj.length)];
        }
        return ret;
    }

    //吃
    public Biology feed(ArrayList<Biology> neighbour) {
        return null;
    }

    //用一串字符串表达这个生物的年龄和状态
    @Override
    public String toString() {
        return "性别:" + (gender ? "雌" : "雄") + "年龄:" + age + "状态:" + (isAlive ? "活" : "死");
    }

    public void notEat() {}
}

