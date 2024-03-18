package tank;


import tank.connection.InputConnection;
import tank.connection.OutputConnection;
import tank.model.Tank;
import tank.server.ServerThread;
import tank.view.Scena;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TankApplication {
    public static Scena scena;
    public static JFrame frame;
    public static List<Scena> list = new ArrayList<>();

    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];
//        Frame frame0 = new Frame(gd0.getDefaultConfiguration());
//        Scena scena=new Scena(tank);
//        tankThread.insertTank(frame0,tank,scena);
//        scena.tankThread=tankThread;
//        frame0.add(scena);
//        frame0.setVisible(true);


        Tank tank = new Tank(1, 0, 100);
        ServerThread tankThread = new ServerThread();
        OutputConnection outputConnection = new OutputConnection();


        frame = new JFrame(gd0.getDefaultConfiguration());
        frame.setTitle("Танки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(true);
        createScena(tank, tankThread, outputConnection);
        frame.add(scena);
        frame.setVisible(true);
        InputConnection inputConnection = new InputConnection();
        inputConnection.setScena(scena);
        inputConnection.start();
        outputConnection.start();
        tankThread.start();
        list.add(scena);

    }

    private static Scena createScena(Tank tank, ServerThread tankThread, OutputConnection outputConnection) {
        scena = new Scena();
        tankThread.insertTank(frame, tank, scena);
        scena.tankThread = tankThread;
        scena.outputConnection = outputConnection;
        return scena;
    }


}
