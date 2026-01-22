package infrastructure;

import service.TaskServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketManager {

    private final int port;
    private final TaskServer taskServer;
    private ExecutorService executorService;

    public ServerSocketManager(int port, TaskServer taskServer) {
        this.port = port;
        this.taskServer = taskServer;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        executorService = Executors.newFixedThreadPool(20);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            ClientHandler handler = new ClientHandler(clientSocket, taskServer);
            executorService.submit(handler);
        }
    }

    public void shutDown() {
        executorService.shutdown();
    }
}
