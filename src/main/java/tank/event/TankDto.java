package tank.event;

import java.awt.*;
import java.io.Serializable;

public class TankDto implements Serializable {
    private static final long serialVersionUID = 8038539938717817115L;
    private int X = 0;
    private int Y = 0;

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLUE);
        g.fillOval(X, 200 + Y, 50, 50);
    }

    @Override
    public String toString() {
        return "TankDto{" +
               "X=" + X +
               ", Y=" + Y +
               '}';
    }
}
