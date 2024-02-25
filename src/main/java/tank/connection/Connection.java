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

    @Override
    public void run() {
        startConnection();
        while (true) {

        }
    }

    public void startConnection() {
        while (!isConnected) {
            try {
                System.out.println("Start");
                socketOut = new Socket(HOST, PORT);
                isConnected = true;
                System.out.println("Connection establishment");
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
}
