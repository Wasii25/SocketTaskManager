package infrastructure;

import presentation.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketManager {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private final CommandParser commandParser;
    public ServerSocketManager(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while(true) {
            String inputLine = in.readLine();
            String commandOutput = commandParser.parseCommand(inputLine);
            if(".".equals(inputLine) || commandOutput == null) {
                out.println("Goodbye");
                stop();
                break;
            }
            out.println(commandOutput);
        }

    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
