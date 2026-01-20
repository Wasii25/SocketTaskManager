package infrastructure;

import service.TaskServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketManager {

    private final int port;
    private final TaskServer taskServer;

    public ServerSocketManager(int port, TaskServer taskServer) {
        this.port = port;
        this.taskServer = taskServer;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            ClientHandler handler = new ClientHandler(clientSocket, taskServer);
            new Thread(handler).start();
        }
    }
}
