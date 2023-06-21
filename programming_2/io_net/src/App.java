package programming_2.io_net.src;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class App {
    public static void main(String[] args) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        URL url = null;
        final String urlString = "http://";
        final String fileName = "output.txt";

        // readResourceStream
        try {
            url = new URL(urlString);
            inputStream = url.openStream();
        } catch (MalformedURLException e) {
            // TODO: handle exception
        } catch (IOException e) {
            // TODO: handle exception
        }

        // writeResourceStream
        try {
            outputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        }

        if (inputStream != null && outputStream != null) {
            // the stream can just be copied if both streams are not null
            try (InputStream in = inputStream; OutputStream out = outputStream) {
                copyStream(in, out);
            } catch (IOException e) {
                // TODO: handle exception
            } catch (NullPointerException e) {
                // TODO: handle exception
            }
            // Not using the finally statement because I would have to nest another try
            // catch block, Therefore, I use a try with resources because it makes sure to
            // close the streams and makes the code neater to read
        }

    }

    private static void copyStream(InputStream in, OutputStream out)
            throws IOException {
        int oneByte = in.read();
        while (oneByte >= 0) { // negative value indicates end-of-stream
            out.write(oneByte);
            oneByte = in.read();
        }
    }
}
