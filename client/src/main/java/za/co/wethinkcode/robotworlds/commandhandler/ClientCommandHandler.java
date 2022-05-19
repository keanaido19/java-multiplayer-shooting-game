package za.co.wethinkcode.robotworlds.commandhandler;

import za.co.wethinkcode.robotworlds.commands.ClientCommand;

/**
 * The Client Command Handler.
 */
public class ClientCommandHandler {
    private static final ClientCommandHandlerStrategy[]
            clientCommandHandlerStrategies =
            {
                    new TurnCommandHandler(),
                    new LaunchCommandHandler()
            };

    private static String getCommand(String commandInput) {
        return commandInput.split(" ")[0];
    }

    private static String[] getCommandArguments(String commandInput) {
        String[] commandAndArguments =
                commandInput.split(" ", 2);
        return commandAndArguments.length < 2 ?
                new String[] {} : commandAndArguments[1].split(" ");
    }

    /**
     * Gets the appropriate client command from the command input by
     * iterating through a list of handler strategies.
     *
     * @param commandInput the command input
     * @return a client command
     */
    public static ClientCommand getClientCommand(String commandInput) {
        String command = getCommand(commandInput);
        String[] commandArguments = getCommandArguments(commandInput);

        for (ClientCommandHandlerStrategy clientCommandHandlerStrategy :
                clientCommandHandlerStrategies) {
            if (clientCommandHandlerStrategy.checkCommand(
                    command,
                    commandArguments)
            ) {
                return clientCommandHandlerStrategy.getCommand();
            }
        }
        return new ClientCommand(command, commandArguments);
    }
}
