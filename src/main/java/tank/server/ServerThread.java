package tank.server;

import tank.event.KeyEventDto;
import tank.model.Tank;
import tank.view.Scena;

import javax.swing.*;

public class ServerThread extends Thread{
    private boolean alive = true;
    private Tank tank;
    private JFrame frame;
    private Scena scena;
    public void insertTank (JFrame frame, Tank tank, Scena scena) {
        this.frame = frame;
        this.tank=tank;
        this.scena = scena;
    }
    @Override
    public void run () {
        while (alive) {
            tank.move(frame);
            scena.updateTankMapWithDto(tank);
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
