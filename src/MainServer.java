import domain.Task;
import infrastructure.ServerSocketManager;
import presentation.CommandParser;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
//        ServerSocketManager server = new ServerSocketManager(new CommandParser());
//        server.start(9000);
        Task task1 = new Task(1, "do laundry");
        Task task2 = new Task(2, "buy groceries");

        System.out.println(task1);  // Should print: 1. do laundry [OPEN]
        System.out.println(task2);  // Should print: 2. buy groceries [OPEN]
        System.out.println(task1.getId());    // Should print: 1
        System.out.println(task1.getTitle()); // Should print: do laundry
    }
}