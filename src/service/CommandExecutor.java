package service;

import presentation.ParsedCommand;

public class CommandExecutor {

    private final TaskServer taskServer;

    public CommandExecutor(TaskServer taskServer) {
        this.taskServer = taskServer;
    }

    public String execute(ParsedCommand cmd) {

        return switch (cmd.name) {
            case "help" -> """
                    Available commands:
                    LOGIN <username>
                    LOGOUT
                    CREATE_TASK <title>
                    LIST_TASKS
                    HELP
                    QUIT
                    """;

            case "create_task" -> {
                if (cmd.argument.isEmpty()) {
                    yield "ERROR: CREATE_TASK requires a title";
                }
                var task = taskServer.createTask(cmd.argument);
                yield "Task created: #" + task.getId() + " " + task.getTitle();
            }

            case "list_tasks" ->
                    taskServer.getAllTasks().isEmpty()
                            ? "No tasks yet"
                            : taskServer.getAllTasks().toString();

            default -> "Invalid command";
        };
    }
}
