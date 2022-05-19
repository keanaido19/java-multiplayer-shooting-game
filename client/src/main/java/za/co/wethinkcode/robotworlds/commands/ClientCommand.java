package za.co.wethinkcode.robotworlds.commands;

import com.google.gson.JsonObject;
import za.co.wethinkcode.robotworlds.Client;
import za.co.wethinkcode.robotworlds.Helpers;

public class ClientCommand {
    protected final String command;
    protected final String[] commandArguments;

    public ClientCommand(String command, String[] commandArguments) {
        this.command = command;
        this.commandArguments = commandArguments;
    }

    public JsonObject execute(Client client) {

        return Helpers.createClientResponse(
                client.getRobotName(),
                command,
                commandArguments
        );
    }
}
