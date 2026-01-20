import domain.Task;
import infrastructure.ServerSocketManager;
import presentation.CommandParser;
import service.TaskServer;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocketManager server = new ServerSocketManager(9000, new TaskServer());
        server.start();
    }
}