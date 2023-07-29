import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.util.Scanner;

import utils.ServerService;

/**
 * The main() program in this class is designed to read requests from
 * a Web browser and display the requests on standard output. The
 * program sets up a listener on port 8081. It can be contacted
 * by a Web browser running on the same machine using a URL of the
 * form http://localhost:8081/resource.suffix This method
 * returns files or html depending on if found the resource or not
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
				ConnectionThread thread = new ConnectionThread(connection);
				thread.start();
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
				serverService.getFile(connection, fileName);
			} catch (ConnectIOException e) {
				throw new Exception("Error while communicating with client: " + e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // make SURE connection is closed before returning!
			try {
				connection.close();
			} catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}
	}

	/**
	 * To help with multithreading
	 */
	private static class ConnectionThread extends Thread {
		Socket connection;

		public ConnectionThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			handleConnection(connection);
		}
	}
}
