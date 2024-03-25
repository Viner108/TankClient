package tank.server;

import tank.MyObjectInputStream;
import tank.dto.KeyEventDto;
import tank.dto.TankDto;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMock {

    public ServerSocket serverSocketOutput;

    private static int PORT_OUTPUT = 8003;

    public ServerSocket serverSocketInput;

    public Socket socketInput;

    public Socket socketOutput;

    private static int PORT_INPUT = 8004;

    public ServerMock() {
    }

    public boolean startConnection() {
        try {
            serverSocketInput = new ServerSocket(PORT_INPUT);
            serverSocketOutput = new ServerSocket(PORT_OUTPUT);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean acceptSocketInput() {
        try {
            socketInput = serverSocketInput.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean acceptSocketOutput() {
        try {
            socketOutput = serverSocketOutput.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public KeyEventDto read() {
        KeyEventDto keyEventDto = null;
        try {
            InputStream inputStream = socketInput.getInputStream();
            ObjectInputStream objectInputStream = new MyObjectInputStream(inputStream);
            keyEventDto = (KeyEventDto) objectInputStream.readObject();
            System.out.println(keyEventDto.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return keyEventDto;
    }

    public void write(TankDto tankDto) {
        try {
            OutputStream outputStream = socketOutput.getOutputStream();
            ObjectOutputStream objectInputStream = new ObjectOutputStream(outputStream);
            objectInputStream.writeObject(tankDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeAfterEach(){
        try {
            if (socketInput!=null) {
                socketInput.close();
            }
            if (socketOutput!=null) {
                socketOutput.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeAfterAll(){
        try {
            serverSocketInput.close();
            serverSocketOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
