package tank;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class MyObjectInputStream extends ObjectInputStream {
    public MyObjectInputStream(InputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void readStreamHeader() throws IOException, StreamCorruptedException {

    }

}
