package za.co.wethinkcode.robotworlds.jsonresponse.objects;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void getX() {
        Position position;

        position = new Position(List.of(1, 2));
        assertEquals(1, position.getX());

        position = new Position(List.of(99, 10));
        assertEquals(99, position.getX());

        position = new Position(List.of(121212, -1212));
        assertEquals(121212, position.getX());
    }

    @Test
    public void getY() {
        Position position;

        position = new Position(List.of(1, 2));
        assertEquals(2, position.getY());

        position = new Position(List.of(99, 10));
        assertEquals(10, position.getY());

        position = new Position(List.of(121212, -1212));
        assertEquals(-1212, position.getY());
    }
}