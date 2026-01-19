package presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {

    public String parseCommand(String userCommand) {
        List<String> parts = Arrays.stream(userCommand.trim().split("\\s+"))
                .toList();
        String command = parts.getFirst();
        String argument = parts.size() > 1 ? parts.get(1) : "";

        String availableCommands = "\"Available commands: LOGIN <username>, LOGOUT, CREATE_TASK <title>, LIST_TASKS, HELP, QUIT\"";
        return switch (command.toLowerCase()) {
            case "help" ->availableCommands ;
            case "login" ->  parts.size() > 1 ? "logged in as " + parts.get(1) : "ERROR: Login requires username";
            case "logout" -> "logged out";
            case "list_tasks" -> "no tasks yet";
            case "quit" -> null;
            default -> "invalid command";
        };
    }
}
