package tank;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class MyObjectInputStream extends ObjectInputStream {
    public MyObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected void readStreamHeader() {

    }


}
