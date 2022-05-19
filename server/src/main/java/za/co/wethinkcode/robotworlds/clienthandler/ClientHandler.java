package za.co.wethinkcode.robotworlds.clienthandler;

import za.co.wethinkcode.robotworlds.JsonHandler;
import za.co.wethinkcode.robotworlds.clienthandler.commands.auxiliarycommands.LookWorldCommand;
import za.co.wethinkcode.robotworlds.response.ServerResponse;
import za.co.wethinkcode.robotworlds.response.ServerResponseBuilder;
import za.co.wethinkcode.robotworlds.world.World;
import za.co.wethinkcode.robotworlds.world.objects.robots.Robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final BufferedReader clientInput;
    private final PrintStream clientOutput;
    private final ServerSocket serverSocket;
    private final String clientIpAddress;
    private final World world;
    private final Socket socket;

    private final ServerResponseBuilder responseBuilder =
            new ServerResponseBuilder();

    private String previousServerResponse = null;
    private Robot robot;

    public ClientHandler(
            ServerSocket serverSocket,
            World world
    ) throws IOException {
        this.serverSocket = serverSocket;
        this.world = world;
        this.socket = serverSocket.accept();

        clientOutput = new PrintStream(socket.getOutputStream(), true);
        clientInput = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        clientIpAddress = socket.getInetAddress().getHostName();
        System.out.println("Client (" + clientIpAddress + ") Has Connected!");
    }

    public Robot getRobot() {
        return robot;
    }

    public World getWorld() {
        return world;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    private void closeClientHandler() {
        world.removeObjectFromWorld(robot);
        try {
            if (socket != null) socket.close();
            if (clientInput != null) clientInput.close();
            if (clientOutput != null) clientOutput.close();
        } catch(IOException e) {e.printStackTrace();}
    }

    private void monitorServerSocketConnection() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (serverSocket.isClosed()) {
                        closeClientHandler();
                        break;
                    }
                }
            }
        }).start();
    }

    private synchronized void sendResponseToClient(String jsonStringResponse) {
        if (clientOutput != null) {
            clientOutput.println(jsonStringResponse);
        }
    }

    private class ClientInputHandler implements Runnable {
        private final String inputFromClient;
        private final ClientHandler clientHandler;

        public ClientInputHandler(
                String inputFromClient,
                ClientHandler clientHandler
        ) {
            this.inputFromClient = inputFromClient;
            this.clientHandler = clientHandler;
        }

        @Override
        public void run() {
            String jsonStringResponse =
                    responseBuilder.getJsonStringResponse(inputFromClient);
            sendResponseToClient(jsonStringResponse);
        }
    }

    public synchronized void updateClientHandler() {
        if (robot == null)
            return;

        LookWorldCommand lookWorldCommand =
                new LookWorldCommand(robot.getName());
        ServerResponse serverResponse =
                lookWorldCommand.execute(this);

        String jsonStringResponse =
                JsonHandler.convertJavaObjectToJsonString(serverResponse);

        if (jsonStringResponse.equals(previousServerResponse)) return;
        previousServerResponse = jsonStringResponse;

        sendResponseToClient(jsonStringResponse);
    }

    private class Loll implements Runnable {

        @Override
        public void run() {
            updateClientHandler();
        }
    }

    private class ClientUpdater implements Runnable {

        @Override
        public void run() {
            while (!serverSocket.isClosed()) {
                new Thread(new Loll()).start();
            }
        }
    }

    public void run() {
        monitorServerSocketConnection();
        new Thread(new ClientUpdater()).start();
        responseBuilder.setupServerResponseBuilder(this);
        try {
            String inputFromClient;
            while (!serverSocket.isClosed() &&
                    (inputFromClient = clientInput.readLine()) != null)
            {
                Thread.sleep(66);
                new Thread(
                        new ClientInputHandler(inputFromClient, this)
                ).start();
            }
        } catch(IOException | InterruptedException ignored) {}
        System.out.println(
                "Client (" + clientIpAddress + ") Has Disconnected!"
        );
        closeClientHandler();
    }
}
