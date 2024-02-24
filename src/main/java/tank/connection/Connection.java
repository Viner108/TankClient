package tank.connection;

import tank.event.KeyEventDto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection extends Thread {
    private static String HOST = "192.168.1.107";
    private static int PORT = 8081;
    private boolean isConnected = false;
    private Socket socketOut;
    private OutputStream out;
    private ObjectOutputStream oos;

    @Override
    public void run() {
        startConnection();
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startConnection() {
        while (!isConnected) {
            try {
                System.out.println("Start");
                socketOut = new Socket(HOST, PORT);
                out = socketOut.getOutputStream();
                oos = new ObjectOutputStream(out);
//                KeyEventDto dtoOut = new KeyEventDto();
//                oos.writeObject(dtoOut);
                isConnected=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void closeConnection() throws IOException {
        socketOut.close();
    }
}
