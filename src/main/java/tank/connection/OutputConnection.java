package tank.connection;

import tank.event.KeyEventDto;

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
    private int i;


    @Override
    public void run() {
        while (true) {
            if (socketOut == null || !isConnected()) {
                i=0;
                startConnection();
            }
        }
    }

    public void startConnection() {
        try {
            System.out.println("Start");
            socketOut = new Socket(HOST, PORT);
            outputStream = socketOut.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            System.out.println("OutputConnection establishment");
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
            KeyEventDto keyEventDto = new KeyEventDto();
            keyEventDto.setKeyCode(i);
            objectOutputStream.writeObject(keyEventDto);
            i++;
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("It isn't connection");
            return false;
        }
        return true;
    }
}
