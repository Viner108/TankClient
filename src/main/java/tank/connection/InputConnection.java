package tank.connection;

import tank.event.TankDto;
import tank.model.Tank;
import tank.view.Scena;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class InputConnection extends Thread {
    private static String HOST = "192.168.1.105";
    private  int PORT = 8002;
    private Socket socketInput;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private Scena scena;



    @Override
    public void run() {
        startConnection();
        while (true) {
            if (socketInput==null){
                startConnection();
            }
            if (socketInput!=null&&!socketInput.isClosed()) {
                read();
            }
        }
    }

    public void startConnection() {
        try {
            System.out.println("Start");
            socketInput = new Socket(HOST, PORT);
            inputStream = socketInput.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            System.out.println("InputConnection establishment");
            read();
        } catch (Exception e) {
            try {
                Thread.sleep(500);
                System.out.println("Try connection");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public TankDto read() {
        TankDto tankDto = null;
        try {
            Map<Integer, Tank> tankMap = new HashMap<>();
            while (true) {
                tankDto = (TankDto) objectInputStream.readObject();
                System.out.println(tankDto.toString());
                Tank tank =new Tank(tankDto.getId(),tankDto.getX(),tankDto.getY());
                tank.setAlpha(tankDto.getAlpha());
                tank.setDeltaAlpha(tankDto.getDeltaAlpha());
                tank.setSpeedAlpha(tankDto.getSpeedAlpha());
                tankMap.put(tankDto.getId(),tank);
                scena.setTanks(tankMap);
            }
        } catch (EOFException e) {
            System.out.println("End of stream");
            startConnection();
        } catch (Exception e) {
            System.out.println("It isn't connection");
            startConnection();
        }
        return tankDto;
    }
    public void closeSocket(){
        try {
            socketInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Scena getScena() {
        return scena;
    }

    public void setScena(Scena scena) {
        this.scena = scena;
    }

    public  int getPORT() {
        return PORT;
    }

    public  void setPORT(int PORT) {
        this.PORT = PORT;
    }
}
