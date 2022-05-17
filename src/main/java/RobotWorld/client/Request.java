package RobotWorld.client;
import org.json.simple.JSONObject;

import java.util.Arrays;

public class Request {

    private String robotName;
    private String command;
    private String argument ;

    public Request(String robotName) {
        this.robotName = robotName;
    }

    public JSONObject CreateRequest(String UserInput){
        JSONObject request = new JSONObject();

        command = UserInput.split("")[0];
        argument = Arrays.toString(new String[]{UserInput.split("")[1]});

        request.put("robot",robotName);
        request.put("command", command);
        request.put("argument", argument);

        return request;
    }


}
