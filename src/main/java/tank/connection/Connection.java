package tank.connection;

import java.io.*;
import java.net.Socket;

public class Connection extends Thread {
    private static String HOST = "192.168.1.105";
    private static final int KEY_EVENT_OUT_PORT = 8001;
    private static final int TANK_IN_PORT = 8002;
    private boolean isConnected = false;
    private boolean isTankConnected = false;
    private boolean isEventConnected = false;
    private Socket eventClientSocket;
    private Socket tankClientSocket;
    private EventConnection eventConnection;
    private TankConnection tankConnection;
    private OutputStream out;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    @Override
    public void run() {
        startConnection();
        creatEventConnection();
        createTankConnection();
        System.out.println("TankClientConnection run end");
//        while (true) {
//            try {
//                out = eventClientSocket.getOutputStream();
//                oos = new ObjectOutputStream(out);
//                DataOutputStream dataOutputStream = new DataOutputStream(out);
//                String string = "run";
//                dataOutputStream.writeUTF(string);
//            } catch (Exception e) {
//                e.printStackTrace();
//                try {
//                    isConnected = false;
//                    Thread.sleep(5000);
//                    startConnection();
//                } catch (InterruptedException ex) {
//                    e.printStackTrace();
//                }
//            }
//        }
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
                System.out.println("TankClientConnection startServerSocket " + TANK_IN_PORT);
                eventClientSocket = new Socket(HOST, KEY_EVENT_OUT_PORT);
                tankClientSocket = new Socket(HOST,TANK_IN_PORT);
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
    private void creatEventConnection () {
        while (!isEventConnected) {
            try {
                System.out.println("TankClientConnection startServerSocket clientSocket" + eventClientSocket == null);
                oos = new ObjectOutputStream(eventClientSocket.getOutputStream());
                isEventConnected = true;
                System.out.println("TankClientConnection startServerSocket receiver");
                eventConnection.setObjectOutputStreamSender(oos);
            } catch (IOException e) {
                System.out.println("TankClientConnection ObjectInputStream error");
            }
        }
    }
    private void createTankConnection () {
        while (!isTankConnected) {
            try {
                System.out.println("TankClientConnection startServerSocket clientSocket" + tankClientSocket == null);
                ois = new ObjectInputStream(tankClientSocket.getInputStream());
                System.out.println("TankClientConnection startServerSocket receiver");
                tankConnection.setReceiver(ois);
                tankConnection.start();
                isTankConnected = true;
            } catch (IOException e) {
                System.out.println("TankClientConnection ObjectInputStream error");
            }
        }
    }

    public void closeConnection() throws IOException {
        eventClientSocket.close();
        tankClientSocket.close();
    }
}
