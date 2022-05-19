package RobotWorld.sever.world;
import RobotWorld.Position;
import RobotWorld.server.world.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorldConfigTest {
    @Test
    void testgetWidth(){
        Config config = new Config(200,400,10);
        assertEquals(config.getWidth(),200);

    }
    @Test
    void testgetHeight(){
        Config config = new Config(200,400,10);
        assertEquals(config.getHeight(),400);

    }
    @Test
    void testgetmaxShield(){
        Config config = new Config(200,400,10);
        assertEquals(config.getMaximumShield(),10);

    }
    @Test
    void bottomRight(){
        Config config = new Config(200,400,10);
        assertEquals(config.bottomRight(),new Position(100,-200));

    }
    @Test
    void topLeft(){
        Config config = new Config(200,400,10);
        assertEquals(config.topLeft(),new Position(-100,200));

    }
    @Test
    void testConfig(){
        Config config = new Config(200,400,10);
        assertNotNull(config);

    }


}
