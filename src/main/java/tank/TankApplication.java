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
import tank.view.Frame;

public class TankApplication {
    public static Scena scena;
    public static JFrame frame;
    public static List<Scena> list = new ArrayList<>();

    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];

        Tank tank = new Tank(1, 0, 100);
        ServerThread tankThread = new ServerThread();
        OutputConnection outputConnection = new OutputConnection();


        Frame frame0 = new Frame(gd0.getDefaultConfiguration());
        createScena(tank, tankThread, outputConnection);
        tankThread.insertTank(frame0,tank,scena);
        scena.tankThread=tankThread;
        frame0.add(scena);
        frame0.setVisible(true);

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
