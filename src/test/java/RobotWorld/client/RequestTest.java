package RobotWorld.client;
import RobotWorld.robot.Robot;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {
    @Test
    void Requests(){
        Request request = new Request("HAL");
        assertNotNull(request.CreateRequest("forward 10"));

    }
    @Test
    void testJson(){
        Request robot = new Request("HAL");
        assertThat(robot.CreateRequest("forward"),instanceOf(JSONObject.class));
    }
}
