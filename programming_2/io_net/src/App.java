package programming_2.io_net.src;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import programming_2.io_net.utils.StreamHandler;

public class App {
    public static void main(String[] args) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        URL url = null;
        final String urlString = "https://filesamples.com/samples/document/txt/sample3.txt";
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
                StreamHandler.copyStream(in, out);
            } catch (IOException e) {
                System.out.println("Sorry, the file you tried to copy wasn't executed successfully, please try again");
            }
            // Not using the finally statement because I would have to nest another try
            // catch block, Therefore, I use a try with resources because it makes sure to
            // close the streams and makes the code neater to read
        }
    }
}
