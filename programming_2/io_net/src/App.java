package programming_2.io_net.src;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import programming_2.io_net.utils.StreamHandler;

public class App {
    private static InputStream inputStream = null;
    private static OutputStream outputStream = null;
    private final static Scanner scanner = new Scanner(System.in);
    private static URL url = null;

    public static void main(String[] args) {
        final String urlString = "https://filesamples.com/samples/document/txt/sample3.txt";
        String fileName = "";

        // readResourceStream
        readResourceStream(urlString);

        fileName = getFileNameFromUser(fileName);

        // // writeResourceStream
        writeResourceStream(fileName);
    }

    private static void closeIOStreams() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing stream");
        }
    }

    private static void closeScanner() {
        scanner.close();
    }

    private static String getFileNameFromUser(String fileName) {
        while (fileName == "") {
            System.out.println("Please enter a filename that is not empty to save the output");
            fileName = scanner.nextLine().trim();
        }
        fileName = fileName.replace(" ", "_");
        fileName = fileName + ".txt";
        return fileName;
    }

    private static void readResourceStream(String urlString) {
        try {
            url = new URL(urlString);
            inputStream = url.openStream();
        } catch (MalformedURLException e) {
            System.out.println("Sorry, the Url you provided was wrong, please try again");
            exitApp();
        } catch (IOException e) {
            System.out.println("The resource you are trying to copy can not be reached or doesn't exist");
            exitApp();
        }
    }

    private static void writeResourceStream(String fileName) {
        try {
            outputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, the path for the file you provided wasn't adequate, please try again");
        }

        if (inputStream != null && outputStream != null) {
            // the stream can just be copied if both streams are not null
            try {
                StreamHandler.copyStream(inputStream, outputStream);
            } catch (IOException e) {
                System.out.println("Sorry, the file you tried to copy wasn't executed successfully, please try again");
            } finally {
                exitApp();
            }
        }
    }

    private static void exitApp() {
        closeIOStreams();
        closeScanner();
        System.exit(0);
    }
}
