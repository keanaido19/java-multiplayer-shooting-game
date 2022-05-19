package za.co.wethinkcode.robotworlds.jsonresponse.objects;

import java.util.List;

/**
 * Position Class
 */
public class Position {
    private final int x;
    private final int y;

    /**
     * Instantiates a new Position.
     *
     * @param position the position
     */
    public Position(List<Integer> position) {
        this.x = position.get(0);
        this.y = position.get(1);
    }

    /**
     * Gets the X coordinate.
     *
     * @return the X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate.
     *
     * @return the Y coordinate
     */
    public int getY() {
        return y;
    }
}
