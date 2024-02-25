package tank;


import tank.connection.Connection;
import tank.connection.FullConnection;
import tank.model.Tank;
import tank.server.ServerThread;
import tank.view.Scena;
import tank.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class TankApplication {

    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];

        FullConnection connection=new FullConnection();
        connection.start();
//
//        Tank tank=new Tank("1",0,100);
//        ServerThread tankThread = new ServerThread();

//        Frame frame0 = new Frame(gd0.getDefaultConfiguration());
//        tankThread.insertTank(frame0,tank);
//        Scena scena=new Scena(tank);
//        scena.tankThread=tankThread;
//        frame0.add(scena);
//        frame0.setVisible(true);

//        JFrame frame = new JFrame(gd0.getDefaultConfiguration());
//        frame.setTitle("Танки");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1000, 1000);
//        frame.setResizable(true);
//        Scena scena=new Scena(tank);
//        tankThread.insertTank(frame,tank,scena);
//        scena.tankThread=tankThread;
//        frame.add(scena);
//        frame.setVisible(true);

//        tankThread.start();

    }


}
