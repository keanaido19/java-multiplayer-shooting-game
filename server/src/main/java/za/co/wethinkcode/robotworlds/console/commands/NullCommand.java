package za.co.wethinkcode.robotworlds.console.commands;

import za.co.wethinkcode.robotworlds.Server;

public class NullCommand extends ServerCommand{
    @Override
    public boolean execute(Server server) {
        System.out.println("Invalid Command!");
        return true;
    }
}
