package za.co.wethinkcode.robotworlds.console.commands;

import za.co.wethinkcode.robotworlds.Server;

public class QuitCommand extends ServerCommand{
    @Override
    public boolean execute(Server server) {
        System.out.println("Shutting down...");
        server.stopServer();
        return false;
    }
}
