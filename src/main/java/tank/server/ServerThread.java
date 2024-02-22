package tank.server;

import tank.event.KeyEventDto;
import tank.model.Tank;
import tank.view.Frame;

import javax.swing.*;

public class ServerThread extends Thread{
    private boolean alive = true;
    private Tank tank;
    private JFrame frame;
    public void insertTank (JFrame frame, Tank tank) {
        this.frame = frame;
        this.tank=tank;
    }
    @Override
    public void run () {
        while (alive) {
            tank.moveX(frame);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void keyPressed (KeyEventDto e) {
        tank.keyEventPressed(e);
    }

    public void keyReleased (KeyEventDto e) {
        tank.keyEventReleased(e);
    }
}
