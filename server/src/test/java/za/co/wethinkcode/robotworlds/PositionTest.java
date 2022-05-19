package za.co.wethinkcode.robotworlds;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import za.co.wethinkcode.robotworlds.world.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PositionTest {
    @Test
    void getPositionAsList(){
        Position position = new Position(1,1);
        assertEquals(position.getPositionAsList(),Arrays.asList(1,1));

    }
    @Test
    void testgetX(){
        Position position =new Position(1,1);
        assertThat(position.getX(),instanceOf(Integer.class));
    }
    @Test
    void testgetY(){
        Position position =new Position(1,1);
        assertThat(position.getY(),instanceOf(Integer.class));
    }
    @Test
    void testToString() {
        Position position =new Position(1,1);
        assertEquals(position.toString(), "(" + 1 + "," + 1 + ")");
    }
}
