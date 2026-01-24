package service;

import domain.UserSession;
import presentation.ParsedCommand;

public class CommandExecutor {

    private final TaskServer taskServer;

    public CommandExecutor(TaskServer taskServer) {
        this.taskServer = taskServer;
    }

    public String execute(ParsedCommand cmd, UserSession session) {

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

            case "login" -> {
                if (cmd.argument.isEmpty()) {
                    yield "ERROR: LOGIN requires username";
                }
                if (session.isLoggedIn()) {
                    yield "Already logged in as " + session.getUsername();
                }
                session.login(cmd.argument);
                yield "Logged in as " + cmd.argument;
            }

            case "create_task" -> {
                if (!session.isLoggedIn()) {
                    yield "ERROR: Please login first";
                }
                if (cmd.argument.isEmpty()) {
                    yield "ERROR: CREATE_TASK requires a title";
                }

                var task = taskServer.createTask(
                        cmd.argument,
                        session.getUsername()
                );

                yield "Task created: #" + task.getId() + " " + task.getTitle();
            }


            case "list_tasks" -> {
                if (!session.isLoggedIn()) {
                    yield "ERROR: Please login first";
                }

                var visibleTasks =
                        taskServer.getVisibleTasksFor(session.getUsername());

                yield visibleTasks.isEmpty()
                        ? "No visible tasks"
                        : visibleTasks.toString();
            }

            case "logout" -> {
                session.logout();
                yield "Logged out";
            }


            default -> "Invalid command";
        };
    }
}
