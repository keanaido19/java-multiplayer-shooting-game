package RobotWorld.sever.world;
import RobotWorld.server.world.DefaultConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class testDefaultConfig {
    @Test
    void testDefaultConfig(){
        DefaultConfig defaultConfig = new DefaultConfig();
        assertNotNull(defaultConfig);
    }
}
