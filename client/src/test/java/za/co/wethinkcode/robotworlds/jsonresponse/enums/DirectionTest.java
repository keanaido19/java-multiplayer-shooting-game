package za.co.wethinkcode.robotworlds.jsonresponse.enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void getDegrees() {
        Direction direction;

        direction = Direction.NORTH;
        assertEquals(direction.getDegrees(), 0);

        direction = Direction.SOUTH;
        assertEquals(direction.getDegrees(), 180);

        direction = Direction.EAST;
        assertEquals(direction.getDegrees(), 270);

        direction = Direction.WEST;
        assertEquals(direction.getDegrees(), 90);
    }
}