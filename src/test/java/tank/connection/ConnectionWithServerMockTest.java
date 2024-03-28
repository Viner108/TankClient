package tank.connection;


import org.junit.jupiter.api.*;
import tank.TankApplication;
import tank.dto.KeyEventDto;
import tank.dto.TankDto;
import tank.server.ServerMock;

import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionWithServerMockTest {
    static ServerMock serverMock;
    OutputConnection outputConnection;
    InputConnection inputConnection;

    @BeforeAll
    static void init() {
        serverMock = new ServerMock();
        serverMock.startConnection();
        System.out.println("Before All");
    }

    @BeforeEach
    void prepare() {
        outputConnection = new OutputConnection();
        outputConnection.setPORT(8004);
        inputConnection = new InputConnection();
        inputConnection.setPORT(8003);
        outputConnection.start();
        inputConnection.start();
        System.out.println("Before Each");
    }

    @Test
    @DisplayName("ServerSocket open")
    void openServerSocketTest() {
        assertFalse(serverMock.serverSocketInput.isClosed());
        assertFalse(serverMock.serverSocketOutput.isClosed());
        System.out.println("ServerSocket close: " + serverMock.serverSocketInput.isClosed());
        System.out.println("ServerSocket close: " + serverMock.serverSocketOutput.isClosed());
    }

    @Test
    @DisplayName("SocketInput connected")
    void connectionToServerFromTwoSocket() {
        assertTrue(serverMock.acceptSocketInput());
        assertTrue(serverMock.acceptSocketOutput());
        System.out.println("ServerSocket close: " + serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: " + serverMock.socketInput.isClosed());
        System.out.println("SocketOutput close: " + serverMock.socketOutput.isClosed());
    }

    @Test
    @DisplayName("Reading an KeyDto from SocketInput")
    void readObject() {
        assertTrue(serverMock.acceptSocketInput());
        System.out.println("ServerSocket close: " + serverMock.serverSocketInput.isClosed());
        System.out.println("SocketInput close: " + serverMock.socketInput.isClosed());
        Assertions.assertEquals(serverMock.read().getKeyCode(), new KeyEventDto().getKeyCode());
    }

    @Test
    @DisplayName("Writing an TankDto from SocketOutput")
    void writeObject() {
        assertTrue(serverMock.acceptSocketOutput());
        System.out.println("ServerSocket close: " + serverMock.serverSocketOutput.isClosed());
        System.out.println("SocketInput close: " + serverMock.socketOutput.isClosed());
        serverMock.write(new TankDto(10));
        Assertions.assertEquals(inputConnection.read(), new TankDto(10));
    }

    @Test
    @DisplayName("Start many clients")
    void testForManyClients() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        String[] args = new String[1];
        for (int i = 0; i < 2; i++) {
            TankApplication.main(args);

        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    TankApplication.list.get(finalI).mouseClicked2(10, 10);
                    for (int i = 0; i < 10; i++) {
                        TankApplication.list.get(finalI).driveForward();
                    }
                }
            });
        }
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void closeConnection() {
        inputConnection.closeSocket();
        outputConnection.closeSocket();
        serverMock.closeAfterEach();
        System.out.println("After Each");
    }

    @AfterAll
    static void close() {
        serverMock.closeAfterAll();
        System.out.println("After All");
    }


}
