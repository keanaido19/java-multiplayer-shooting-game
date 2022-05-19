package za.co.wethinkcode.robotworlds;

import com.google.gson.JsonObject;
import za.co.wethinkcode.robotworlds.commandhandler.ClientCommandHandler;
import za.co.wethinkcode.robotworlds.commands.ClientCommand;
import za.co.wethinkcode.robotworlds.commands.LaunchCommand;
import za.co.wethinkcode.robotworlds.controller.GamePadInput;
import za.co.wethinkcode.robotworlds.jsonresponse.JsonResponseGeneral;
import za.co.wethinkcode.robotworlds.jsonresponse.enums.CommandResult;
import za.co.wethinkcode.robotworlds.turtle.TurtleScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client Class
 */
public class Client {
    private final TurtleScreen turtle = new TurtleScreen();
    private final Client client = this;

    private final Thread consoleThread =
            new Thread(new ClientConsole());
    private final Thread responseThread =
            new Thread(new ListenForServerResponse());
    private final Thread gamepadThread =
            new Thread(new GamePadInput(this));

    private final String ipAddress;
    private final int port;

    private BufferedReader serverOutput;
    public PrintStream serverInput;
    private String robotName;
    private Socket socket;
    private boolean isLaunchCommand = false;

    public String tempRobotName;

    /**
     * Instantiates a new Client.
     *
     * @param ipAddress the ip address
     * @param port      the port
     */
    public Client(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Gets robot name.
     *
     * @return the robot name
     */
    public String getRobotName() {
        return robotName;
    }

    private void bindSocket() throws IOException {
        System.out.println("Attempting to establish connection to server...");
        socket = new Socket(ipAddress, port);
    }

    private void setServerInputStream() throws IOException {
        serverInput = new PrintStream(socket.getOutputStream(), true);
    }

    private void setServerOutputStream() throws IOException {
        serverOutput = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
    }

    private void setServerIOStreams() throws IOException {
        setServerInputStream();
        setServerOutputStream();
    }

    private void stopClient() {
        try {
            if (socket != null) socket.close();
            if (serverInput != null) serverInput.close();
            if (serverOutput != null) serverOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Shutting down client...");
        System.exit(0);
    }

    /**
     * Send request to server.
     *
     * @param userInput the user input
     */
    public synchronized void sendRequestToServer(String userInput) {
        if ("quit".equals(userInput)) {
            System.out.println("Client has disconnected from server!");
            stopClient();
        }

        ClientCommand clientCommand =
                ClientCommandHandler.getClientCommand(userInput);

        if (clientCommand instanceof LaunchCommand) {
            isLaunchCommand = true;
        }

        JsonObject clientJsonRequest = clientCommand.execute(client);
        try {
            Thread.sleep(66);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        serverInput.println(clientJsonRequest.toString());
    }

    private class ClientRequestHandler implements Runnable {
        private final String inputFromClient;

        /**
         * Instantiates a new Client request handler.
         *
         * @param inputFromClient the input from client
         */
        public ClientRequestHandler(String inputFromClient) {
            this.inputFromClient = inputFromClient;
        }

        @Override
        public void run() {
            sendRequestToServer(inputFromClient);
        }
    }

    private class ClientConsole implements Runnable {

        @Override
        public void run() {
            System.out.println("Console is ready for user input:");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    new Thread(
                            new ClientRequestHandler(scanner.nextLine())
                    ).start();
                }
            }
        }
    }

    private boolean isResponseForUI(String jsonString) {
        JsonResponseGeneral jsonResponse = (JsonResponseGeneral)
                JsonHandler.convertJsonStringToObject(
                        jsonString,
                        JsonResponseGeneral.class
                );

        if (jsonResponse == null) return false;
        return CommandResult.DRAW.equals(jsonResponse.getResult());
    }

    private boolean isResponseSuccessful(String jsonString) {
        JsonResponseGeneral jsonResponse = (JsonResponseGeneral)
                JsonHandler.convertJsonStringToObject(
                        jsonString,
                        JsonResponseGeneral.class
                );
        return CommandResult.OK.equals(jsonResponse.getResult());
    }

    private class TurtleResponseHandler implements Runnable {
        private final String jsonStringResponseFromServer;

        private TurtleResponseHandler(String jsonStringResponseFromServer) {
            this.jsonStringResponseFromServer = jsonStringResponseFromServer;
        }

        @Override
        public void run() {
            if (isResponseForUI(jsonStringResponseFromServer)) {
                turtle.processJsonStringResponse(jsonStringResponseFromServer);
            }
        }
    }

    private synchronized void printServerResponse(
            String jsonStringResponseFromServer
    ) {
        System.out.println(jsonStringResponseFromServer);
    }

    private class TextResponseHandler implements Runnable {
        private final String jsonStringResponseFromServer;

        /**
         * Instantiates a new Text response handler.
         *
         * @param jsonStringResponseFromServer the json string response from server
         */
        public TextResponseHandler(String jsonStringResponseFromServer) {
            this.jsonStringResponseFromServer = jsonStringResponseFromServer;
        }

        @Override
        public void run() {
            if (isLaunchCommand) {
                if (isResponseForUI(jsonStringResponseFromServer)) {
                    new Thread(
                            new TurtleResponseHandler(
                                    jsonStringResponseFromServer)
                    ).start();
                    return;
                }
                if (isResponseSuccessful(jsonStringResponseFromServer)) {
                    robotName = tempRobotName;
                }
                isLaunchCommand = false;
            }

//            if (!isResponseForUI(jsonStringResponseFromServer))
//                printServerResponse(jsonStringResponseFromServer);
        }
    }

    private class ServerResponseHandler implements Runnable {
        private final String jsonStringResponseFromServer;

        /**
         * Instantiates a new Server response handler.
         *
         * @param jsonStringResponseFromServer the json string response from server
         */
        public ServerResponseHandler(String jsonStringResponseFromServer) {
            this.jsonStringResponseFromServer = jsonStringResponseFromServer;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(66);
                new Thread(
                        new TextResponseHandler(jsonStringResponseFromServer)
                ).start();
                new Thread(
                        new TurtleResponseHandler(jsonStringResponseFromServer)
                ).start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class ListenForServerResponse implements Runnable {

        @Override
        public void run() {
            String jsonStringResponseFromServer;
            try {
                while (true) {
                    jsonStringResponseFromServer = serverOutput.readLine();
                    if (jsonStringResponseFromServer == null) break;
                    new Thread(
                            new ServerResponseHandler(
                                    jsonStringResponseFromServer)
                    ).start();
                }
                System.out.println("Connection to server has been lost!");
                stopClient();
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * Start client.
     */
    public void startClient() {
        System.out.println("Starting client...");
        try {
            bindSocket();
            setServerIOStreams();
            System.out.println("Connection to server has been established " +
                    "successfully!");
//            consoleThread.start();
            responseThread.start();
            gamepadThread.start();
        } catch (IOException e) {
            System.out.println("Failed to establish connection to " +
                    "server!\nShutting down client...");
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (Helpers.checkValidIpAndPort(args)) {
            String ipAddress = args[0];
            int port = Integer.parseInt(args[1]);

            Client client = new Client(ipAddress, port);
            client.startClient();
        } else {
            System.out.println("Invalid (IP Address)/PORT!!!");
        }
    }
}
