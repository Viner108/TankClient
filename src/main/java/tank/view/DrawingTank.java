package tank.view;

import tank.model.Tank;

import javax.swing.*;
import java.awt.*;

public class DrawingTank extends JPanel {
    Tank tank;

    public DrawingTank(Tank tank) {
        this.tank = tank;
        setBackground(new Color(34, 139, 34));
    }
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        tank.draw(g);
        repaint();
//        g.setColor(Color.RED);
//        g.fillOval(100,100,50,50);
    }
}
