package za.co.wethinkcode.robotworlds;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotworlds.world.data.WorldData;

public class WorldDataTest {
    @Test
    void testgetWorld() {
        WorldData worldData = new WorldData();
        assertEquals(worldData.getWidth(),0);

    }
    @Test
    void testgetWorldHeight() {
        WorldData worldData = new WorldData();
        assertEquals(worldData.getHeight(),0);

    }
    @Test
    void testgetWorldShield() {
        WorldData worldData = new WorldData();
        assertEquals(worldData.getShields(),0);

    }
    @Test
    void testgetWorldShot() {
        WorldData worldData = new WorldData();
        assertEquals(worldData.getShots(),0);

    }
}
