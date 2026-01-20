package presentation;

public class ParsedCommand {
    public final String name;
    public final String argument;

    public ParsedCommand(String name, String argument) {
        this.name = name;
        this.argument = argument;
    }
}
