package tank;


import tank.model.Tank;
import tank.server.ServerThread;
import tank.view.Scena;
import tank.view.Frame;

import javax.swing.*;
import java.awt.*;

public class TankApplication {

    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];

        Tank tank=new Tank("1",0,100);
        ServerThread tankThread = new ServerThread();

//        Frame frame0 = new Frame();
//        tankThread.insertTank(frame0,tank);
//        Scena scena=new Scena(tank);
//        scena.tankThread=tankThread;
//        frame0.add(scena);
//        frame0.setVisible(true);

        JFrame frame = new JFrame(gd0.getDefaultConfiguration());
        frame.setTitle("Танки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setResizable(true);
        tankThread.insertTank(frame,tank);
        Scena scena=new Scena(tank);
        scena.tankThread=tankThread;
        frame.add(scena);
        frame.setVisible(true);

        tankThread.start();

    }


}
