package tank.connection;

import tank.event.KeyEventDto;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection extends Thread {
    private static String HOST = "192.168.1.105";
    private static int PORT = 8001;
    private boolean isConnected = false;
    private Socket socketOut;
    private OutputStream out;
    private ObjectOutputStream oos;

    @Override
    public void run() {
        startConnection();
        while (true) {
            try {
                out = socketOut.getOutputStream();
                oos = new ObjectOutputStream(out);
                DataOutputStream dataOutputStream = new DataOutputStream(out);
                String string = "run";
                dataOutputStream.writeUTF(string);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    isConnected = false;
                    Thread.sleep(5000);
                    startConnection();
                } catch (InterruptedException ex) {
                    e.printStackTrace();
                }
            }
        }
//        try {
//            while (!isConnected) {
//                closeConnection();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void startConnection() {
        while (!isConnected) {
            try {
                System.out.println("Start");
                socketOut = new Socket(HOST, PORT);
                isConnected = true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeConnection() throws IOException {
        socketOut.close();
    }
}
