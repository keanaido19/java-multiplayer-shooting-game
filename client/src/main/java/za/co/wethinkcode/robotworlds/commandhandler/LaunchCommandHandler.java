package za.co.wethinkcode.robotworlds.commandhandler;

import za.co.wethinkcode.robotworlds.Helpers;
import za.co.wethinkcode.robotworlds.commands.ClientCommand;
import za.co.wethinkcode.robotworlds.commands.LaunchCommand;

import java.util.Arrays;
import java.util.regex.Pattern;

public class LaunchCommandHandler extends ClientCommandHandlerStrategy{
    @Override
    public boolean checkCommand(String command, String[] commandArguments) {
        this.command = command;
        this.commandArguments = commandArguments;
        return "launch".equals(command) && checkArguments(commandArguments);
    }

    @Override
    protected boolean checkArguments(String[] commandArguments) {
        Pattern pattern = Pattern.compile(
                "^\\[" +
                        "[a-zA-Z]+,\\s" +
                        "[a-zA-Z]+,\\s" +
                        "\\d+,\\s" +
                        "\\d+]$"
        );

        if (pattern.matcher(Arrays.toString(commandArguments)).find()) {
            return Helpers.isPositiveInteger(commandArguments[2]) &&
                    Helpers.isPositiveInteger(commandArguments[3]);
        }
        return false;
    }

    @Override
    public ClientCommand getCommand() {
        String robotName = commandArguments[0];
        String[] launchCommandArguments =
                Arrays.copyOfRange(commandArguments, 1, 4);
        return new LaunchCommand(robotName, launchCommandArguments);
    }
}