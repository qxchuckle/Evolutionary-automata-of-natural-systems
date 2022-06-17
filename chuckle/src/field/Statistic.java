package field;

//统计类
public class Statistic {
    private double totalAge;
    private int number;

    public void increaseNumber(){
        this.number++;
    }

    public double averageAge(){
        return totalAge*1.0/number;
    }

    public void initialization(){
        this.number = 0;
        this.totalAge = 0;
    }

    public double getTotalAge() {
        return totalAge;
    }
    public void setTotalAge(double totalAge) {
        this.totalAge = totalAge;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
