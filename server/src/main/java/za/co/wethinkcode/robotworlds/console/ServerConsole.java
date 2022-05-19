package za.co.wethinkcode.robotworlds.console;

import za.co.wethinkcode.robotworlds.Server;
import za.co.wethinkcode.robotworlds.console.commandhandler.ServerCommandHandler;
import za.co.wethinkcode.robotworlds.console.commands.ServerCommand;

import java.util.Scanner;

public class ServerConsole implements Runnable{
    private final Server server;

    public ServerConsole(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            String userInput = scanner.nextLine();
            ServerCommand serverCommand =
                    ServerCommandHandler.getServerCommand(userInput);
            continueLoop = serverCommand.execute(server);
        }
    }
}
