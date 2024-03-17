package tank.server;

import tank.MyObjectInputStream;
import tank.event.KeyEventDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMock {
    ServerSocket serverSocketOutput;
    private static int PORT_OUTPUT = 8002;
    public ServerSocket serverSocketInput;
    public Socket socketInput;
    public Socket socketOutput;
    private static int PORT_INPUT = 8001;
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
    public boolean acceptSocketInput(){
        try {
            socketInput = serverSocketInput.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean acceptSocketOutput(){
        try {
            socketOutput = serverSocketOutput.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public KeyEventDto read(){
        KeyEventDto keyEventDto=null;
        try {
            InputStream inputStream = socketInput.getInputStream();
            ObjectInputStream objectInputStream = new MyObjectInputStream(inputStream);
            keyEventDto= (KeyEventDto) objectInputStream.readObject();
            System.out.println(keyEventDto.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return keyEventDto;
    }
}
