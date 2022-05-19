package RobotWorld.client;
import org.json.simple.JSONArray;
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
        JSONArray data = new JSONArray();

        command = UserInput.split("")[0];
        argument = UserInput.split("")[1];
        data.add(argument);

        request.put("robot",robotName);
        request.put("command", command);
        request.put("argument", data);

        return request;
    }


}
