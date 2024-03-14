package tank.connection;

import tank.MyObjectInputStream;
import tank.event.KeyEventDto;
import tank.event.TankDto;
import tank.model.Tank;
import tank.view.Scena;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class InputConnection extends Thread {
    private static String HOST = "192.168.1.105";
    private static int PORT = 8002;
    private Socket socketOut;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private Scena scena;

    public InputConnection(Scena scena) {
        this.scena = scena;
    }

    @Override
    public void run() {
        while (true) {
            if (socketOut == null || !isConnected()) {
                startConnection();
            }
        }
    }

    public void startConnection() {
        try {
            System.out.println("Start");
            socketOut = new Socket(HOST, PORT);
            inputStream = socketOut.getInputStream();
            objectInputStream = new MyObjectInputStream(inputStream);
            System.out.println("InputConnection establishment");
            isConnected();
        } catch (Exception e) {
            try {
                Thread.sleep(500);
                System.out.println("Try connection");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        try {
            Map<Integer,TankDto> tanks = (Map<Integer, TankDto>) objectInputStream.readObject();
            Map<Integer, Tank> tankMap = new HashMap<>();
            for (TankDto tankDto : tanks.values()) {
                tankMap.put(tankDto.getId(),new Tank(tankDto.getId(),tankDto.getX(),tankDto.getY()));
                System.out.println(tankDto.toString());
            }
            scena.setTanks(tankMap);
        } catch (Exception e) {
            System.out.println("It isn't connection");
            return false;
        }
        return true;
    }

}
