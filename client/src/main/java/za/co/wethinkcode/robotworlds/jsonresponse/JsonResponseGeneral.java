package za.co.wethinkcode.robotworlds.jsonresponse;

import za.co.wethinkcode.robotworlds.jsonresponse.enums.CommandResult;

public class JsonResponseGeneral {
    private CommandResult result;
    private Object data;
    private Object state;

    public CommandResult getResult() {
        return result;
    }
}
