package presentation;
//This class is solely to parse the command given by the user

public class CommandParser {

    public ParsedCommand parse(String userCommand) {
        String[] parts = userCommand.trim().split("\\s+", 2);

        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        return new ParsedCommand(command, argument);
    }
}
