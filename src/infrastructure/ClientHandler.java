package infrastructure;

import presentation.CommandParser;
import service.CommandExecutor;
import presentation.ParsedCommand;
import service.TaskServer;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final CommandParser parser;
    private final CommandExecutor executor;

    public ClientHandler(Socket clientSocket, TaskServer taskServer) {
        this.clientSocket = clientSocket;
        this.parser = new CommandParser();
        this.executor = new CommandExecutor(taskServer);
    }

    @Override
    public void run() {
        System.out.println("Client connected from " + clientSocket.getRemoteSocketAddress());

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true)
        ) {
            String inputLine;

            while ((inputLine = in.readLine()) != null) {

                if (inputLine.equalsIgnoreCase("quit")) {
                    out.println("Goodbye");
                    System.out.println("Client disconnected");
                    break;
                }

                ParsedCommand command = parser.parse(inputLine);
                String response = executor.execute(command);

                out.println(response);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected");
        }
    }
}
