package tank;


import tank.model.Tank;
import tank.server.ServerThread;
import tank.view.DrawingTank;
import tank.view.Scene;

import javax.swing.*;
import java.awt.*;

public class TankApplication {

    public static void main(String[] args) {
        Tank tank=new Tank("1",0,100);
        ServerThread tankThread = new ServerThread();
        Scene scene =new Scene();
        tankThread.insertTank(scene,tank);
        scene.add(new DrawingTank(tank));
        scene.setVisible(true);
        tankThread.start();

    }


}
