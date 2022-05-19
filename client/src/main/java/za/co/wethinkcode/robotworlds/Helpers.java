package za.co.wethinkcode.robotworlds;

import com.google.gson.*;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * The Helpers Class.
 */
public class Helpers {

    /**
     * Checks if a given string is a positive integer.
     *
     * @param integer a given string
     * @return boolean value
     */
    public static boolean isPositiveInteger(String integer) {
        try {
            Pattern pattern = Pattern.compile("^\\d+$");
            int x = Integer.parseInt(integer);
            return pattern.matcher(integer).find() && x >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given arguments consists of valid ip address and port
     * number.
     *
     * @param args The given string arguments
     * @return Boolean value
     */
    public static boolean checkValidIpAndPort(String[] args) {
        Pattern pattern = Pattern.compile("^\\[" +
                "(localhost|" +
                "(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5]))" +
                ",\\s\\d+]$");

        if (pattern.matcher(Arrays.toString(args)).find()) {
            if (isPositiveInteger(args[1])) {
                int port = Integer.parseInt(args[1]);
                return port <= 65535;
            }
        }
        return false;
    }

    /**
     * Creates a client response json object.
     *
     * @param robotName        the name of the robot
     * @param command          the command
     * @param commandArguments the command arguments
     * @return json object to used by the client
     */
    public static JsonObject createClientResponse(
            String robotName,
            String command,
            String[] commandArguments)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("robot", robotName);
        jsonObject.addProperty("command", command);

        JsonArray jsonArray = JsonParser.parseString(
                Arrays.toString(commandArguments)).getAsJsonArray();
        jsonObject.add("arguments", jsonArray);

        return jsonObject;
    }
}
