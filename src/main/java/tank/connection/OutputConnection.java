package tank.connection;

import tank.MyObjectOutputStream;
import tank.event.KeyEventDto;
import tank.event.TankDto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class OutputConnection extends Thread {
    private static String HOST = "192.168.1.105";
    private static int PORT = 8001;
    private Socket socketOut;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private static KeyEventDto keyEventDto = new KeyEventDto();


    @Override
    public void run() {
        startConnection();
        while (true) {
            if (!socketOut.isClosed()) {
                keyPressed(keyEventDto);
            }
        }
    }

    public void startConnection() {
        try {
            System.out.println("Start");
            socketOut = new Socket(HOST, PORT);
            outputStream = socketOut.getOutputStream();
            objectOutputStream = new MyObjectOutputStream(outputStream);
            System.out.println("OutputConnection establishment");
        } catch (Exception e) {
            try {
                Thread.sleep(500);
                System.out.println("Try connection");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void keyPressed(KeyEventDto e) {
        if (objectOutputStream != null) {
            try {
                synchronized (objectOutputStream) {
                    objectOutputStream.writeObject(e);
                }
            } catch (IOException ex) {
                System.out.println("It isn't connection");
                startConnection();
            }
        }
    }

    //
    public void keyReleased(KeyEventDto e) {
        if (objectOutputStream != null) {
            try {
                synchronized (objectOutputStream) {
                    objectOutputStream.writeObject(e);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
