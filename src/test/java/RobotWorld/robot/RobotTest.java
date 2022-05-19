package RobotWorld.robot;
import RobotWorld.Position;


import RobotWorld.server.world.obstacle.Obstacle;
import RobotWorld.server.world.obstacle.SquareObstacle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {
    @Test
    void testBlockPosition(){
        Obstacle obstacle = new SquareObstacle(5,5);
        assertTrue(obstacle.blocksPosition(new Position(5,5)));
        assertFalse(obstacle.blocksPosition(new Position(5,1)));
        assertFalse(obstacle.blocksPosition(new Position(1,3)));
        assertFalse(obstacle.blocksPosition(new Position(0,5)));
        assertTrue(obstacle.blocksPosition(new Position(6,5)));
    }
    @Test
    void blockspath(){
        Obstacle obstacle = new SquareObstacle(5,5);
        assertTrue(obstacle.blocksPath(new Position(5,0), new Position(5,50)));
        assertFalse(obstacle.blocksPath(new Position(2,-10), new Position(2, 100)));
        assertTrue(obstacle.blocksPath(new Position(-10,5), new Position(20, 5)));
        assertFalse(obstacle.blocksPath(new Position(0,5), new Position(0, 100)));
        assertFalse(obstacle.blocksPath(new Position(5,6), new Position(5, 100)));

    }
    @Test
    void testgetShield(){
        Robot robot = new Robot("HAL");
        int shield = 0;
        assertEquals(robot.getShield(),shield);


    } @Test
    void testName(){
        Robot robot = new Robot("HAL");
        String name = "HAL";
        assertEquals(robot.getName(),name);
    }

}
