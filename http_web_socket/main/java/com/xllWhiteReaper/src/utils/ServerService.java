package utils;

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

import models.Status;

public class ServerService {
    private final String FILES_DIRECTORY = "main/java/com/xllWhiteReaper/src/root";
    private final String OK = "OK";
    private final int OK_CODE = 200;
    // private final String BAD_REQUEST = "BAD_REQUEST";
    // private final int BAD_REQUEST_CODE = 400;
    // private final String NOT_FOUND = "NOT_FOUND";
    // private final int NOT_FOUND_CODE = 404;
    // private final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    // private final int INTERNAL_SERVER_ERROR_CODE = 500;
    final Map<String, String> mimeTypeMap = new HashMap<String, String>();
    final Map<String, Status> fullErrorNameMap = Map.of(
            "OK", new Status(200, "OK", "The request was successfully placed"),
            "BAD_REQUEST",
            new Status(400, "BAD_REQUEST",
                    "The link provided by the user is malformed due to invalid syntax or missing parameters, please provide a file name with a valid suffix"),
            "NOT_FOUND", new Status(404, "NOT_FOUND", "The resource that you requested does not exist on this server."),
            "INTERNAL_SERVER",
            new Status(500, "INTERNAL_SERVER", "There was an error in the server, please try again later."));

    public ServerService() {
        fillHashMap();
    }

    public void getFile(Socket connectionSocket, String fileName) throws ConnectIOException {
        StringBuilder stringBuilder = new StringBuilder();
        int statusCode = OK_CODE;
        String requestStatus = OK;
        File requestedFile = new File(FILES_DIRECTORY + fileName);
        final long fileSize = requestedFile.isFile() && requestedFile.canRead() ? requestedFile.length() : 0;
        String contentType = getMimeType(fileName);
        try (PrintWriter printWriter = new PrintWriter(connectionSocket.getOutputStream())) {
            if (!requestedFile.isFile() && fileName.indexOf(".") == -1) {
                Status status = fullErrorNameMap.get("BAD_REQUEST");
                // statusCode = BAD_REQUEST_CODE;
                // requestStatus = BAD_REQUEST;
                System.out.println("Directory");
                displayErrorFallbackHTML(connectionSocket, status.getTextStatus());
                return;
            } else if (!requestedFile.exists()) {
                Status status = fullErrorNameMap.get("NOT_FOUND");
                // statusCode = NOT_FOUND_CODE;
                // requestStatus = NOT_FOUND;
                displayErrorFallbackHTML(connectionSocket, status.getTextStatus());
                return;
            } else if (requestedFile.canRead()) {
                // There were no errors
                printWriter.print("HTTP/1.1 " + statusCode + " " + requestStatus + "\r\n");
                printWriter.print("Connection: close" + "\r\n");
                printWriter.print("Content-Length: " + fileSize + "\r\n");
                printWriter.print("Content-Type: " + contentType + "\r\n");
                printWriter.print("\r\n");
                printWriter.flush();
                try {
                    sendFile(requestedFile, connectionSocket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            throw new ConnectIOException("There was an error with the connection");
        } finally {
            closeConnection(connectionSocket);
        }
    }

    private void displayErrorFallbackHTML(Socket connectionSocket, String requestStatus) {
        Status status = fullErrorNameMap.get(requestStatus);
        int statusCode = status.getCode();
        System.out.println("displaying html");
        String response = "<html><head><title>Error</title></head><body>\r\n" + //
                "<h2>Error: " + statusCode + " " + requestStatus + "</h2>\r\n" + //
                "<p>" + status.getMessage() + "</p>\r\n" + //
                "</body></html>";

        System.out.println("response");
        System.out.println(response);

        try (PrintWriter printWriter = new PrintWriter(connectionSocket.getOutputStream())) {
            printWriter.print("HTTP/1.1 " + statusCode + " " + requestStatus + "\r\n");
            printWriter.print("Connection: close" + "\r\n");
            // printWriter.print("Content-Length: " + 1024 + "\r\n");
            printWriter.print("Content-Type: " + mimeTypeMap.get("html") + "\r\n");
            printWriter.print("\r\n");
            printWriter.print(response);
            printWriter.flush();

            // try (OutputStream out = new
            // BufferedOutputStream(connectionSocket.getOutputStream());) {
            // for (byte currentByte : response.getBytes()) {
            // System.out.println("Current byte " + currentByte);
            // out.write(currentByte);
            // }
            // out.flush();
            // } catch (IOException e) {
            // System.out.println("Error");
            // }

            // printWriter.print(response);
            // printWriter.flush();
        } catch (Exception e) {
            closeConnection(connectionSocket);
        }
    }

    private void sendFile(File file, OutputStream socketOut) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(file));
                OutputStream out = new BufferedOutputStream(socketOut);) {
            while (true) {
                int x = in.read(); // read one byte from file
                if (x < 0)
                    break; // end of file reached
                out.write(x); // write the byte to the socket
            }
            out.flush();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void closeConnection(Socket connection) {
        try {
            connection.close();
        } catch (Exception e) {
        }
        System.out.println("Connection closed.");
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
