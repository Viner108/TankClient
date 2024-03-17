package tank.connection;

import tank.MyObjectInputStream;
import tank.event.KeyEventDto;
import tank.event.TankDto;
import tank.model.Tank;
import tank.view.Scena;

import java.awt.event.KeyEvent;
import java.io.*;
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
        startConnection();
        while (true) {
            if (!socketOut.isClosed()) {
                isConnected();
            }
        }
    }

    public void startConnection() {
        try {
            System.out.println("Start");
            socketOut = new Socket(HOST, PORT);
            inputStream = socketOut.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
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

    public void isConnected() {
        try {
            Map<Integer, Tank> tankMap = new HashMap<>();
            while (true) {
                TankDto tankDto = (TankDto) objectInputStream.readObject();
                Tank tank = new Tank(tankDto.getId(), tankDto.getX(), tankDto.getY());
                System.out.println(tankDto.toString());
                tankMap.put(tankDto.getId(),new Tank(tankDto.getId(),tankDto.getX(),tankDto.getY()));
                System.out.println(tankDto.toString());
                scena.setTanks(tankMap);
            }
//            Map<Integer,TankDto> tanks = (Map<Integer, TankDto>) objectInputStream.readObject();
//            for (TankDto tankDto : tanks.values()) {
//                tankMap.put(tankDto.getId(),new Tank(tankDto.getId(),tankDto.getX(),tankDto.getY()));
//                System.out.println(tankDto.toString());
//            }
        } catch (EOFException e) {
            System.out.println("End of stream");
            startConnection();
        } catch (Exception e) {
            System.out.println("It isn't connection");
            startConnection();
        }
    }
}
