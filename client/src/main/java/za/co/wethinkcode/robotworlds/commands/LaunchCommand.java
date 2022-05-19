package za.co.wethinkcode.robotworlds.commands;

import com.google.gson.JsonObject;
import za.co.wethinkcode.robotworlds.Client;
import za.co.wethinkcode.robotworlds.Helpers;

public class LaunchCommand extends ClientCommand{
    private final String robotName;

    public LaunchCommand(String robotName, String[] commandArguments) {
        super("launch", commandArguments);
        this.robotName = robotName;
    }

    @Override
    public JsonObject execute(Client client) {
        client.tempRobotName = this.robotName;
        return Helpers.createClientResponse(
                robotName,
                command,
                commandArguments
        );
    }
}
