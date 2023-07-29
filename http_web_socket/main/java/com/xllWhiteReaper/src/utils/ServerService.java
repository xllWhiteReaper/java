package http_web_socket.main.java.com.xllWhiteReaper.src.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerService {
    private final String FILES_DIRECTORY = "main/java/com/xllWhiteReaper/src/root";
    private final String OK = "OK";
    private final int OK_CODE = 200;
    private final String BAD_REQUEST = "BAD_REQUEST";
    private final int BAD_REQUEST_CODE = 400;
    private final String NOT_FOUND = "NOT_FOUND";
    private final int NOT_FOUND_CODE = 404;
    final Map<String, String> mimeTypeMap = new HashMap<String, String>();

    public ServerService() {
        fillHashMap();
    }

    public void getTextFile(Socket connectionSocket, String fileName) throws ConnectIOException {
        StringBuilder stringBuilder = new StringBuilder();
        int statusCode = OK_CODE;
        String requestStatus = OK;
        File requestedFile = new File(FILES_DIRECTORY + fileName);
        final long fileSize = requestedFile.isFile() && requestedFile.canRead() ? requestedFile.length() : 0;
        String contentType = getMimeType(fileName);
        final String fallbackContentType = "text/plain";

        try (PrintWriter printWriter = new PrintWriter(connectionSocket.getOutputStream())) {
            if (!requestedFile.isFile() && fileName.indexOf(".") == -1) {
                contentType = fallbackContentType;
                System.out.println("DIRECTORY");
                statusCode = BAD_REQUEST_CODE;
                requestStatus = BAD_REQUEST;
            } else if (!requestedFile.exists()) {
                contentType = fallbackContentType;
                statusCode = NOT_FOUND_CODE;
                requestStatus = NOT_FOUND;
            } else if (requestedFile.canRead()) {
                try (Scanner scannedFile = new Scanner(requestedFile);) {
                    while (scannedFile.hasNextLine()) {
                        System.out.println("Reading file");
                        stringBuilder.append(scannedFile.nextLine() + "\r\n");
                    }
                } catch (Exception e) {
                    stringBuilder.append("ERROR for file with path " + requestedFile.getAbsolutePath());
                    statusCode = NOT_FOUND_CODE;
                    requestStatus = NOT_FOUND;
                }
            } else {
                statusCode = NOT_FOUND_CODE;
                requestStatus = NOT_FOUND;
            }

            String response = stringBuilder.toString();
            printWriter.print("HTTP/1.1 " + statusCode + " " + requestStatus + "\r\n");
            printWriter.print("Connection: close" + "\r\n");
            printWriter.print("Content-Length: " + fileSize + "\r\n");
            printWriter.print("Content-Type: " + contentType + "\r\n");
            printWriter.print("\r\n");
            printWriter.print(response);
            printWriter.flush();
        } catch (Exception e) {
            throw new ConnectIOException("There was an error with the connection");
        }
    }

    private static void sendFile(File file, OutputStream socketOut) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        OutputStream out = new BufferedOutputStream(socketOut);
        while (true) {
            int x = in.read(); // read one byte from file
            if (x < 0)
                break; // end of file reached
            out.write(x); // write the byte to the socket
        }
        out.flush();
    }

    private String getMimeType(String fileName) {
        final String fallback = "x-application/x-unknown";
        int pos = fileName.lastIndexOf('.');
        if (pos < 0) // no file extension in name
            return fallback;
        String ext = fileName.substring(pos + 1).toLowerCase();
        final String mimeType = mimeTypeMap.get(ext);
        return mimeType == null ? fallback : mimeType;
    }

    private void fillHashMap() {
        mimeTypeMap.put("txt", "text/plain");
        mimeTypeMap.put("html", "text/html");
        mimeTypeMap.put("htm", "text/html");
        mimeTypeMap.put("css", "text/css");
        mimeTypeMap.put("js", "text/javascript");
        mimeTypeMap.put("java", "text/x-java");
        mimeTypeMap.put("jpeg", "image/jpeg");
        mimeTypeMap.put("jpg", "image/jpeg");
        mimeTypeMap.put("png", "image/png");
        mimeTypeMap.put("gif", "image/gif");
        mimeTypeMap.put("ico", "image/x-icon");
        mimeTypeMap.put("class", "application/java-vm");
        mimeTypeMap.put("jar", "application/java-archive");
        mimeTypeMap.put("zip", "application/zip");
        mimeTypeMap.put("xml", "application/xml");
        mimeTypeMap.put("xhtml", "application/xhtml+xml");
    }
}
