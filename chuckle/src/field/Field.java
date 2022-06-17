package field;

import cell.Cell;

import java.util.ArrayList;

//数据类，管理所有格子和生物
public class Field {

    private int width;//网格的宽
    private int height;//网格的高
    private Cell[][] field;//一切实现了Cell这个接口的类的对象都能放进去,用来存生物

    //初始化这个网格
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[height][width];
    }

    //获得宽
    public int getWidth() {
        return width;
    }
    //获得高
    public int getHeight() {
        return height;
    }

    //在网格指定位置放一个生物
    public Cell place(int row, int col, Cell cell) {
        Cell ret = field[row][col];//记录此处当前生物
        field[row][col] = cell;//更新此处生物
        return ret;
    }

    //获得网格指定位置的生物
    public Cell getBiology(int row, int col) {
        return field[row][col];
    }

    //获取指定位置生物的邻居,并打包成一个对象数组
    public Cell[] getNeighbour(int row, int col) {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int r = row + i;
                int c = col + j;
                if (r > -1 && r < height && c > -1 && c < width && !(r == row && c == col)) {
                    //确保不会获得超过网格外的东西，以及生物自己
                    list.add(field[r][c]);//把这个生物放进邻居列表里
                }
            }
        }
        //toArray会自动把一个Cell[]数组装好，然后返回这个数组
        return list.toArray(new Cell[list.size()]);
    }

    //获取指定位置周围的空位，用于放宝宝以及生物移动位置
    public Coordinate[] getFreeNeighbour(int row, int col) {
        ArrayList<Coordinate> list = new ArrayList<Coordinate>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int r = row + i;
                int c = col + j;
                if (r > -1 && r < height && c > -1 && c < width && field[r][c] == null) {
                    list.add(new Coordinate(r, c));
                }
            }
        }
        return list.toArray(new Coordinate[list.size()]);
    }

    //移除指定位置的生物
    public Cell remove(int row, int col) {
        Cell ret = field[row][col];
        field[row][col] = null;
        return ret;
    }

    //移除匹配的生物，因为生物不知道食物的位置，所以不能用上面那个remove
    public void remove(int row,int col,Cell cell) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int r = row + i;
                int c = col + j;
                if (r > -1 && r < height && c > -1 && c < width && !(r == row && c == col)) {
                    //确保不会获得超过网格外的东西，以及生物自己
                    if (field[r][c] == cell) {
                        field[r][c] = null;
                        break;
                    }
                }
            }
        }
    }

    //把宝宝放置在生物周围空位
    public boolean placeBaby(int row, int col, Cell cell) {
        boolean ret = false;
        Coordinate[] freePlace = getFreeNeighbour(row, col);
        if (freePlace.length > 0) {//判断有没有空位
            int n = (int) (Math.random() * freePlace.length);
            field[freePlace[n].getRow()][freePlace[n].getCol()] = cell;
            ret = true;
        }
        return ret;
    }


    //移动
    public void move(int row, int col, Coordinate loc) {
        field[loc.getRow()][loc.getCol()] = field[row][col];
        remove(row, col);
    }
}
