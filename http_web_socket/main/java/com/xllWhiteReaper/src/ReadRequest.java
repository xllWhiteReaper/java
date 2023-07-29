package http_web_socket.main.java.com.xllWhiteReaper.src;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.util.Scanner;

import http_web_socket.main.java.com.xllWhiteReaper.src.utils.ServerService;

/**
 * The main() program in this class is designed to read requests from
 * a Web browser and display the requests on standard output. The
 * program sets up a listener on port 50505. It can be contacted
 * by a Web browser running on the same machine using a URL of the
 * form http://localhost:505050/path/to/resource.html This method
 * does not return any data to the web browser. It simply reads the
 * request, writes it to standard output, and then closes the connection.
 * The program continues to run, and the server continues to listen
 * for new connections, until the program is terminated (by clicking the
 * red "stop" square in Eclipse or by Control-C on the command line).
 */
public class ReadRequest {

	/**
	 * The server listens on this port. Note that the port number must
	 * be greater than 1024 and lest than 65535.
	 */
	private static final int LISTENING_PORT = 8081;

	private static final String FILES_DIRECTORY = "main/java/com/xllWhiteReaper/src/root";
	
	private static final ServerService serverService = new ServerService();

	/**
	 * Main program opens a server socket and listens for connection
	 * requests. It calls the handleConnection() method to respond
	 * to connection requests. The program runs in an infinite loop,
	 * unless an error occurs.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from "
						+ connection.getRemoteSocketAddress());
				handleConnection(connection);
			}
		} catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		}
	}

	private static void handleConnection(Socket connection) {
		try (PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
				Scanner inputStream = new Scanner(connection.getInputStream())) {
			final String[] requestData = inputStream.nextLine().split(" ");
			final String method = requestData[0];
			final String fileName = requestData[1];
			final String transferProtocol = requestData[2];

			try {
				serverService.getTextFile(connection, fileName);
			} catch (ConnectIOException e) {
				System.out.println("Error while communicating with client: " + e);
			}

			// // printWriter.println("Content-Type: text/plain");
			// // printWriter.println("Content: POSTMAN");
			// printWriter.write("{\"name\":\"name1\",\"age\":age1}");
			// printWriter.flush();
			// // while (true) {
			// // if (!in.hasNextLine())
			// // break;
			// // String line = in.nextLine();
			// // if (line.trim().length() == 0)
			// // break;
			// // System.out.println(" " + line);
			// // }
		} catch (Exception e) {
			System.out.println("Error while communicating with client: " + e);
		} finally { // make SURE connection is closed before returning!
			try {
				connection.close();
			} catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}
	}

	/**
	 * Handle communication with one client connection. This method reads
	 * lines of text from the client and prints them to standard output.
	 * It continues to read until the client closes the connection or
	 * until an error occurs or until a blank line is read. In a connection
	 * from a Web browser, the first blank line marks the end of the request.
	 * This method can run indefinitely, waiting for the client to send a
	 * blank line.
	 * NOTE: This method does not throw any exceptions. Exceptions are
	 * caught and handled in the method, so that they will not shut down
	 * the server.
	 * 
	 * @param connection the connected socket that will be used to
	 *                   communicate with the client.
	 */
	// private static void handleConnection(Socket connection) {
	// try {
	// Scanner in = new Scanner(connection.getInputStream());
	// while (true) {
	// if (!in.hasNextLine())
	// break;
	// String line = in.nextLine();
	// if (line.trim().length() == 0)
	// break;
	// System.out.println(" " + line);
	// }
	// } catch (Exception e) {
	// System.out.println("Error while communicating with client: " + e);
	// } finally { // make SURE connection is closed before returning!
	// try {
	// connection.close();
	// } catch (Exception e) {
	// }
	// System.out.println("Connection closed.");
	// }
	// }
}
