package tank.view;

import tank.connection.OutputConnection;
import tank.event.KeyEventDto;
import tank.event.TankDto;
import tank.model.Tank;
import tank.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Scena extends JPanel implements KeyListener, TestForScena{
    Map<Integer, Tank> tanks = new HashMap<>();
    public ServerThread tankThread;
    public OutputConnection outputConnection;

    public Scena() {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        addKeyListener(this);
        this.setFocusable(true);
        grabFocus();
        setBackground(new Color(34, 139, 34));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (tanks) {
            Iterator<Tank> tankIterator = tanks.values().iterator();
            while (tankIterator.hasNext()) {
                Tank t = tankIterator.next();
                t.draw(g);
            }
        }
        repaint();
    }

    public void updateTankMapWithDto(Tank tank) {
        tank.paintCharges();
    }

    public Map<Integer, Tank> getTanks() {
        return tanks;
    }

    public void setTanks(Map<Integer, Tank> tanks) {
        this.tanks = tanks;
    }

    @Override
    public void driveForward() {
        for (int i = 0; i < 10; i++) {
            outputConnection.keyPressed(KeyEventDto.fromKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'), true));
        }
    }
    @Override
    public void driveInCircles() {
        for (int i = 0; i < 20; i++) {
            outputConnection.keyPressed(KeyEventDto.fromKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'), true));
        }
        for (int i = 0; i < 20; i++) {
            outputConnection.keyPressed(KeyEventDto.fromKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D'), true));
        }
        for (int i = 0; i < 20; i++) {
            outputConnection.keyPressed(KeyEventDto.fromKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S'), true));
        }
        for (int i = 0; i < 20; i++) {
            outputConnection.keyPressed(KeyEventDto.fromKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A'), true));
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
//        tankThread.keyPressed(KeyEventDto.fromKeyEvent(e));
        outputConnection.keyPressed(KeyEventDto.fromKeyEvent(e, true));
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        tankThread.keyReleased(KeyEventDto.fromKeyEvent(e));
        outputConnection.keyReleased(KeyEventDto.fromKeyEvent(e, false));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
