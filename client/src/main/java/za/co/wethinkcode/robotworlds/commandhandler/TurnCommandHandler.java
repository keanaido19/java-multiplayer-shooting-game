package za.co.wethinkcode.robotworlds.commandhandler;

import za.co.wethinkcode.robotworlds.commands.ClientCommand;

/**
 * The Turn Command Handler.
 */
public class TurnCommandHandler extends ClientCommandHandlerStrategy{
    @Override
    public boolean checkCommand(String command, String[] commandArguments) {
        this.command = command;
        this.commandArguments = commandArguments;
        return ("left".equals(command) || "right".equals(command)) &&
                checkArguments(commandArguments);
    }

    @Override
    protected boolean checkArguments(String[] commandArguments) {
        return commandArguments.length == 0;
    }

    @Override
    public ClientCommand getCommand() {
        return new ClientCommand(
                "turn",
                new String[] {command}
        );
    }
}
