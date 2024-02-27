package tank.connection;

public interface Connection {
    void run();
    void startConnection();
    boolean isConnected();
}
