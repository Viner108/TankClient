package tank.connection;

import java.io.*;
import java.net.Socket;

public class FullConnection extends Thread {
    private static String HOST = "192.168.1.105";
    private static final int KEY_EVENT_OUT_PORT = 8001;
    private static final int TANK_IN_PORT = 8002;
    private boolean isConnected = false;
    private boolean isTankConnected = false;
    private boolean isEventConnected = false;
    private Socket eventClientSocket;
    private Socket tankClientSocket;
    private EventConnection eventConnection = new EventConnection();
    private TankConnection tankConnection = new TankConnection();
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;

    @Override
    public void run() {
        startConnection();
        creatEventConnection();
        createTankConnection();
        while (true) {
            try{
                sender = new ObjectOutputStream(eventClientSocket.getOutputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(eventClientSocket.getOutputStream());
                String string = "run";
                dataOutputStream.writeUTF(string);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    isConnected = false;
                    System.out.println("TankConnection run end");
                    Thread.sleep(5000);
                    startConnection();
                    creatEventConnection();
                    createTankConnection();
                } catch (InterruptedException ex) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void startConnection() {
        while (!isConnected) {
            try {
                System.out.println("TankConnection startServerSocket " + TANK_IN_PORT);
                eventClientSocket = new Socket(HOST, KEY_EVENT_OUT_PORT);
                tankClientSocket = new Socket(HOST, TANK_IN_PORT);
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

    private void creatEventConnection() {
        while (!isEventConnected) {
            try {
                System.out.println("EventConnection startServerSocket clientSocket" + eventClientSocket);
                sender = new ObjectOutputStream(eventClientSocket.getOutputStream());
                isEventConnected = true;
                System.out.println("EventConnection startServerSocket receiver");
                eventConnection.setObjectOutputStreamSender(sender);
            } catch (IOException e) {
                System.out.println("EventConnection ObjectInputStream error");
                e.printStackTrace();
                try {
                    isConnected = false;
                    Thread.sleep(5000);
                    startConnection();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void createTankConnection() {
        while (!isTankConnected) {
            try {
                System.out.println("TankConnection startServerSocket clientSocket" + tankClientSocket);
                receiver = new ObjectInputStream(tankClientSocket.getInputStream());
                System.out.println("TankConnection startServerSocket receiver");
                tankConnection.setReceiver(receiver);
                tankConnection.start();
                isTankConnected = true;
            } catch (IOException e) {
                System.out.println("TankConnection ObjectInputStream error");
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
    }

    public void closeConnection() throws IOException {
        eventClientSocket.close();
        tankClientSocket.close();
    }
}
