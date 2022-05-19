package za.co.wethinkcode.robotworlds.world.objects.obstacles;

import za.co.wethinkcode.robotworlds.world.enums.ObjectType;
import za.co.wethinkcode.robotworlds.world.Position;
import za.co.wethinkcode.robotworlds.world.objects.WorldObject;

import java.util.ArrayList;
import java.util.List;

public class Boundary extends WorldObject {
    public Boundary(int width, int height) {
        super(0, 0, ObjectType.EDGE, width, height);
    }

    @Override
    public boolean containsPosition(Position p) {
        int x = p.getX();
        int y = p.getY();

        boolean inXRange =
                x >= getMinimumXCoordinate() && x <= getMaximumXCoordinate();
        boolean inYRange =
                y >= getMinimumYCoordinate() && y <= getMaximumYCoordinate();

        boolean atTopEdge = y == getMaximumYCoordinate() && inXRange;
        boolean atBottomEdge = y == getMinimumYCoordinate() && inXRange;
        boolean atLeftEdge = x == getMinimumXCoordinate() && inYRange;
        boolean atRightEdge = x == getMaximumXCoordinate() && inYRange;

        return atTopEdge || atBottomEdge || atLeftEdge || atRightEdge;
    }

    @Override
    public List<Position> getPositionRange() {
        List<Position> positionRange = new ArrayList<>();
        List<Integer> xCoordinateRange = getXCoordinateRange();
        List<Integer> yCoordinateRange = getYCoordinateRange();
        for (int x : xCoordinateRange) {
            for (int y : yCoordinateRange) {
                Position p = new Position(x, y);
                if (containsPosition(p)) positionRange.add(new Position(x, y));
            }
        }
        return positionRange;
    }

    public boolean isPositionInsideBoundary(Position p) {
        return super.containsPosition(p);
    }
}
