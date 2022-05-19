package za.co.wethinkcode.robotworlds.world.objects.obstacles;

import za.co.wethinkcode.robotworlds.world.Position;
import za.co.wethinkcode.robotworlds.world.objects.WorldObject;

/**
 * Square Obstacle Class
 */
public class SquareObstacle extends WorldObject {
    public SquareObstacle(Position centerPosition, int size) {
        super(centerPosition, size);
    }

    @Override
    public String toString() {
        return " * Square Obstacle - From " + getBottomLeftPosition() +
                " To " + getTopRightPosition() +" *";
    }
}
