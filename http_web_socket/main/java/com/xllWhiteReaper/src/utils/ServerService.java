package http_web_socket.main.java.com.xllWhiteReaper.src.utils;

import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.ConnectIOException;
import java.util.Scanner;

public class ServerService {
    private final String FILES_DIRECTORY = "main/java/com/xllWhiteReaper/src/root";

    public void getTextFile(Socket connectionSocket, String fileName) throws ConnectIOException {
        Path currentDirectory = Paths.get("");
        // Get the absolute path of the current working directory
        String absolutePath = currentDirectory.toAbsolutePath().toString();
        // Print the absolute path
        System.out.println("Current working directory: " + absolutePath);

        StringBuilder stringBuilder = new StringBuilder();
        int statusCode = 200;
        File requestedFile = new File(FILES_DIRECTORY + fileName);

        try (PrintWriter printWriter = new PrintWriter(connectionSocket.getOutputStream())) {
            try (Scanner scannedFile = new Scanner(requestedFile);) {
                while (scannedFile.hasNextLine()) {
                    System.out.println("Reading file");
                    stringBuilder.append(scannedFile.nextLine() + "\n");
                }
            } catch (Exception e) {
                stringBuilder.append("ERROR for file with path " + requestedFile.getAbsolutePath());
                statusCode = 404;
            }
            String response = stringBuilder.toString();
            printWriter.println("HTTP/1.1 " + statusCode + " NOT_FOUND");
            printWriter.println("Content-Type: text/plain");
            printWriter.println("Content-Length: " + response.length());
            printWriter.println();
            printWriter.println(response);
            printWriter.flush();
        } catch (Exception e) {
            throw new ConnectIOException("There was an error with the connection");
        }
    }
}
