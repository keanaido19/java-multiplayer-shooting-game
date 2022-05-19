package za.co.wethinkcode.robotworlds;

import za.co.wethinkcode.robotworlds.clienthandler.ClientHandler;
import za.co.wethinkcode.robotworlds.console.ServerConsole;
import za.co.wethinkcode.robotworlds.world.World;
import za.co.wethinkcode.robotworlds.world.builders.WorldBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server {
    public final ServerSocket serverSocket;
    private final World world = WorldBuilder.getWorldWithSquareObstacles();

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public World getWorld() {
        return world;
    }

    public void startServer() throws UnknownHostException {
        System.out.println(
                "Server is running!\nListening on IP: " +
                        InetAddress.getLocalHost().getHostAddress() +
                        "\nPORT: " + serverSocket.getLocalPort()
        );
        System.out.println("Console is ready for user input:");
        while (!serverSocket.isClosed()) {
            try {
                ClientHandler clientHandler =
                        new ClientHandler(serverSocket, world);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                stopServer();
            }
        }
    }

    public void stopServer() {
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Server server = new Server(serverSocket);
        new Thread(new ServerConsole(server)).start();
        server.startServer();
    }
}
