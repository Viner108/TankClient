package tank.view;

import tank.connection.OutputConnection;
import tank.dto.KeyEventDto;
import tank.model.Tank;
import tank.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Scena extends JPanel implements KeyListener, TestForScena {
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

    private void sendPressKey(int vkW, char w, int number) {
        for (int i = 0; i < number; i++) {
            outputConnection.keyPressed(KeyEventDto.fromKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, vkW, w), true));
        }
    }

    @Override
    public void driveForward() {
        sendPressKey(KeyEvent.VK_W, 'W', 20);
        driveInCircles();
    }

    @Override
    public void driveDown() {
        sendPressKey(KeyEvent.VK_D, 'D', 20);
        sendPressKey(KeyEvent.VK_W, 'W', 20);
        driveInCircles();
    }

    @Override
    public void driveForwardAndDownAndBack() {
        sendPressKey(KeyEvent.VK_D, 'D', 20);
        sendPressKey(KeyEvent.VK_W, 'W', 20);
        sendPressKey(KeyEvent.VK_A, 'A', 20);
        sendPressKey(KeyEvent.VK_S, 'S', 20);
        driveInCircles();
    }

    @Override
    public void driveInCircles() {
        sendPressKey(KeyEvent.VK_W, 'W', 20);
        sendPressKey(KeyEvent.VK_D, 'D', 20);
        sendPressKey(KeyEvent.VK_W, 'W', 20);
        sendPressKey(KeyEvent.VK_D, 'D', 20);
        sendPressKey(KeyEvent.VK_S, 'S', 20);
        sendPressKey(KeyEvent.VK_A, 'A', 20);
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
