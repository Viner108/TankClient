package tank.connection;

import java.io.ObjectOutputStream;

public class EventConnection {
    ObjectOutputStream objectOutputStreamSender = null;
    public EventConnection (){

    }

    public void setObjectOutputStreamSender (ObjectOutputStream objectOutputStreamSender) {
        System.out.println("EventClientConnection");
        this.objectOutputStreamSender = objectOutputStreamSender;
    }
}
