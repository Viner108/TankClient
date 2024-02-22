package tank.server;

import tank.model.Tank;
import tank.view.Scene;

import java.util.Map;
import java.util.stream.Collectors;

public class ServerThread extends Thread{
    private boolean alive = true;
    private Tank tank;
    private Scene scene;
    public void insertTank (Scene scene,Tank tank) {
        this.scene=scene;
        this.tank=tank;
    }
    @Override
    public void run () {
        while (alive) {
            tank.moveX(scene);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
