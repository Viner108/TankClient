package tank.view;

import tank.TankApplication;
import tank.connection.OutputConnection;
import tank.dto.KeyEventDto;
import tank.dto.MouseEventDto;
import tank.model.Tank;
import tank.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Scena extends JPanel implements KeyListener, MouseListener {
    Map<Integer, Tank> tanks = new HashMap<>();
    public ServerThread tankThread;
    public OutputConnection outputConnection;

    public Scena() {
        super();
        addMouseListener(this);
        addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        requestFocus();
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
            outputConnection.keyReleased(KeyEventDto.fromKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, vkW, w), false));
        }
    }


    public void driveForward() {
        sendPressKey(KeyEvent.VK_W, 'W', 50);
        driveInCircles();
    }


    public void driveDown() {
        sendPressKey(KeyEvent.VK_W, 'W', 50);
        sendPressKey(KeyEvent.VK_D, 'D', 20);
        sendPressKey(KeyEvent.VK_W, 'W', 50);
        driveInCircles();
    }


    public void driveForwardAndDownAndBack() {
        sendPressKey(KeyEvent.VK_W, 'W', 50);
        sendPressKey(KeyEvent.VK_D, 'D', 20);
        sendPressKey(KeyEvent.VK_W, 'W', 50);
        sendPressKey(KeyEvent.VK_A, 'A', 20);
        sendPressKey(KeyEvent.VK_S, 'S', 20);
        driveInCircles();
    }


    public void driveInCircles() {
        sendPressKey(KeyEvent.VK_W, 'W', 50);
        sendPressKey(KeyEvent.VK_D, 'D', 20);
        sendPressKey(KeyEvent.VK_W, 'W', 50);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        outputConnection.mouseClicked(MouseEventDto.fromMouseEvent(e));
    }
    public void mouseClicked2(int x,int y) {
        outputConnection.mouseClicked(MouseEventDto.fromMouseEvent(new MouseEvent(this, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, x, y, 1, false)));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
