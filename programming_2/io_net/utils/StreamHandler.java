package programming_2.io_net.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamHandler {
    public static void copyStream(InputStream in, OutputStream out)
            throws IOException {
        int oneByte = in.read();
        while (oneByte >= 0) { // negative value indicates end-of-stream
            out.write(oneByte);
            oneByte = in.read();
        }
    }
}
