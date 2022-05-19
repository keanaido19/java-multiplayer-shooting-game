package za.co.wethinkcode.robotworlds.commandhandler;

import za.co.wethinkcode.robotworlds.commands.ClientCommand;

/**
 * The Client Command Handler Strategy.
 */
public abstract class ClientCommandHandlerStrategy {
    /**
     * The name of the command.
     */
    protected String command;
    /**
     * The command arguments.
     */
    protected String[] commandArguments;

    /**
     * Checks if the given command and command arguments are valid.
     *
     * @param command   the name of the command
     * @param commandArguments the command arguments
     * @return boolean value
     */
    public abstract boolean checkCommand(
            String command, String[] commandArguments
    );

    /**
     * Check if the argument(s) for the command are valid.
     *
     * @param commandArguments the command arguments
     * @return the boolean
     */
    protected abstract boolean checkArguments(String[] commandArguments);

    /**
     * @return a client command
     */
    public abstract ClientCommand getCommand();
}
