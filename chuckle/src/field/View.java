package field;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import cell.Cell;

//表现类，将数据拿到后画出画面
public class View extends JPanel {
    private static final int GRID_SIZE = 10;//格子大小
    private Field theField;
    public View(Field field) {
        theField = field;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        for (int row = 0; row < theField.getHeight(); row++) {
            g.drawLine(0, row * GRID_SIZE, theField.getWidth() * GRID_SIZE, row * GRID_SIZE);
        }
        for (int col = 0; col < theField.getWidth(); col++) {
            g.drawLine(col * GRID_SIZE, 0, col * GRID_SIZE, theField.getHeight() * GRID_SIZE);
        }
        for (int row = 0; row < theField.getHeight(); row++) {
            for (int col = 0; col < theField.getWidth(); col++) {
                Cell cell = theField.getBiology(row, col);
                if (cell != null) {
                    cell.draw(g, col * GRID_SIZE, row * GRID_SIZE, GRID_SIZE);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(theField.getWidth() * GRID_SIZE + 1, theField.getHeight() * GRID_SIZE + 1);
    }
}
