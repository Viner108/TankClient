package tank.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InputConnection extends Thread {
    private static int PORT = 8002;
    private ServerSocket serverSocket;
    private Socket input;


    @Override
    public void run() {
        startConnection();
        while (true) {
            try {
                clientConnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startConnection() {
        try {
            System.out.println("Start");
            serverSocket = new ServerSocket(PORT);
            clientConnect();
        } catch (Exception e) {
            try {
                Thread.sleep(500);
                System.out.println("Try connection");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void clientConnect() throws IOException {
        input = serverSocket.accept();
        System.out.println("Client connect");
    }

}
