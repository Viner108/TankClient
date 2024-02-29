package tank.connection;

import tank.MyObjectInputStream;
import tank.event.KeyEventDto;
import tank.event.TankDto;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InputConnection extends Thread implements Connection{
    private static String HOST = "192.168.1.105";
    private static int PORT = 8002;
    private Socket socketOut;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;

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
            TankDto tankDto = (TankDto) objectInputStream.readObject();
            System.out.println(tankDto.toString());
        } catch (Exception e) {
            System.out.println("It isn't connection");
            return false;
        }
        return true;
    }

}
