package cell;

import java.awt.Graphics;//一个用来绘制2D图像必须导入的java包，提供对图形图像的像素，颜色的绘制

//格子类
public interface Cell {
    void draw(Graphics g, int x, int y, int size);
}
