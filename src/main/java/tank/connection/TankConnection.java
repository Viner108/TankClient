package tank.connection;

import tank.event.KeyEventDto;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TankConnection extends Thread {
    ObjectInputStream receiver;
    private boolean isAlive = true;

    public TankConnection() {
        System.out.println("TankClientConnection");
        isAlive = true;
    }

    public void setReceiver(ObjectInputStream receiver) {
        System.out.println("TankClientConnection");
        isAlive = true;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        while (true) {
            try {
                KeyEventDto keyEventDto = (KeyEventDto) receiver.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                if (receiver != null) {
                    receiver.close();
                }
                System.out.println("TankClientConnection close");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("TankClientConnection run end");
        }
    }

    public void stopConnection() {
        isAlive = false;
    }

}