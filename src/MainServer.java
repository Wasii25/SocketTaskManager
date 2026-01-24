import domain.Task;
import domain.UserSession;
import infrastructure.ServerSocketManager;
import presentation.CommandParser;
import repository.SqliteTaskRepository;
import repository.TaskRepository;
import service.TaskServer;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        TaskRepository repo = new SqliteTaskRepository();
        TaskServer taskServer = new TaskServer(repo);

        ServerSocketManager server =
                new ServerSocketManager(9000, taskServer);
        server.start();

    }
}