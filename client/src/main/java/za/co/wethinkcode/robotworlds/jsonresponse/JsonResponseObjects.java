package za.co.wethinkcode.robotworlds.jsonresponse;

import za.co.wethinkcode.robotworlds.jsonresponse.enums.CommandResult;
import za.co.wethinkcode.robotworlds.jsonresponse.objects.RobotState;
import za.co.wethinkcode.robotworlds.jsonresponse.objects.WorldObjectData;

import java.util.LinkedHashMap;
import java.util.List;

public class JsonResponseObjects {
    private CommandResult result;
    private LinkedHashMap<String, List<WorldObjectData>> data;
    private RobotState state;

    public CommandResult getResult() {
        return result;
    }

    public LinkedHashMap<String, List<WorldObjectData>> getData() {
        return data;
    }

    public RobotState getState() {
        return state;
    }
}
