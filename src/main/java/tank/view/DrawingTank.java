package tank.view;

import tank.event.KeyEventDto;
import tank.model.Tank;
import tank.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawingTank extends JPanel implements KeyListener {
    Tank tank;
    ServerThread tankThread;

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
    @Override
    public void keyPressed (KeyEvent e) {
        tankThread.keyPressed(KeyEventDto.fromKeyEvent(e));
    }

    @Override
    public void keyReleased (KeyEvent e) {
        tankThread.keyReleased(KeyEventDto.fromKeyEvent(e));
    }

    @Override
    public void keyTyped (KeyEvent e) {
    }
}
