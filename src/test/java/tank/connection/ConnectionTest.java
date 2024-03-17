package tank.connection;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tank.event.KeyEventDto;
import tank.server.ServerMock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionTest {

    @Test
    @DisplayName("ServerSocket open")
    void openServerSocketTest(){
        ServerMock serverMock = new ServerMock();
        assertTrue(serverMock.startConnection());
        assertFalse(serverMock.serverSocketInput.isClosed());
        System.out.println("ServerSocket close: "+serverMock.serverSocketInput.isClosed());
        try {
            serverMock.serverSocketInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ServerSocket close: "+serverMock.serverSocketInput.isClosed());
    }
    @Test
    @DisplayName("SocketInput connected")
    void connectionToServerFromSocketInput(){
        ServerMock serverMock = new ServerMock();
        OutputConnection outputConnection =new OutputConnection();
        serverMock.startConnection();
        outputConnection.start();
        assertTrue(serverMock.acceptSocketInput());
        System.out.println("ServerSocket close: "+ serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: "+ serverMock.socketInput.isClosed());
        try {
            serverMock.socketInput.close();
            serverMock.serverSocketInput.close();
            outputConnection.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ServerSocket close: "+ serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: "+ serverMock.socketInput.isClosed());
    }
    @Test
    @DisplayName("Reading an KeyDto from SocketInput")
    void readObject(){
        ServerMock serverMock = new ServerMock();
        OutputConnection outputConnection =new OutputConnection();
        serverMock.startConnection();
        outputConnection.start();
        assertTrue(serverMock.acceptSocketInput());
        System.out.println("ServerSocket close: "+ serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: "+ serverMock.socketInput.isClosed());
        Assertions.assertEquals(serverMock.read().getKeyCode(),new KeyEventDto().getKeyCode());
        try {
            serverMock.socketInput.close();
            serverMock.serverSocketInput.close();
            outputConnection.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ServerSocket close: "+ serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: "+ serverMock.socketInput.isClosed());
    }
    @Test
    @DisplayName("SocketInput connected")
    void connectionToServerFromTwoSocket(){
        ServerMock serverMock = new ServerMock();
        OutputConnection outputConnection =new OutputConnection();
        InputConnection inputConnection = new InputConnection();
        outputConnection.start();
        inputConnection.start();
        serverMock.startConnection();
        assertTrue(serverMock.acceptSocketInput());
        assertTrue(serverMock.acceptSocketOutput());
        System.out.println("ServerSocket close: "+ serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: "+ serverMock.socketInput.isClosed());
        System.out.println("SocketOutput close: "+ serverMock.socketOutput.isClosed());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            serverMock.socketInput.close();
            serverMock.socketOutput.close();
            serverMock.serverSocketInput.close();
            outputConnection.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ServerSocket close: "+ serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: "+ serverMock.socketInput.isClosed());
        System.out.println("SocketOutput close: "+ serverMock.socketOutput.isClosed());
    }



}
