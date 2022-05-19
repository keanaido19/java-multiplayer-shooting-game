package za.co.wethinkcode.robotworlds.world.objects;

import za.co.wethinkcode.robotworlds.world.enums.Direction;
import za.co.wethinkcode.robotworlds.world.enums.ObjectType;
import za.co.wethinkcode.robotworlds.world.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldObject {
    private Position centerPosition;
    private Direction direction;
    private ObjectType objectType;
    private boolean hasCollision = true;
    private int width;
    private int height;

    public WorldObject(
            Position centerPosition,
            Direction direction,
            ObjectType objectType,
            int width,
            int height
    ) {
        this.centerPosition = centerPosition;
        this.direction = direction;
        this.objectType = objectType;
        this.width = width;
        this.height = height;
    }

    public WorldObject(
            Position centerPosition,
            Direction direction,
            int width,
            int height
    ) {
        this.centerPosition = centerPosition;
        this.direction = direction;
        this.objectType = ObjectType.OBSTACLE;
        this.width = width;
        this.height = height;
    }

    public WorldObject(
            Position centerPosition,
            Direction direction,
            ObjectType objectType,
            int size
    ) {
        this(centerPosition, direction, objectType, size, size);
    }

    public WorldObject(
            Position centerPosition,
            ObjectType objectType,
            int width,
            int height
    ) {
        this(centerPosition, Direction.NORTH, objectType, width, height);
    }

    public WorldObject(
            Position centerPosition,
            ObjectType objectType,
            int size
    ) {
        this(centerPosition, objectType, size, size);
    }

    public WorldObject(Position centerPosition, int width, int height) {
        this(centerPosition, ObjectType.OBSTACLE, width, height);
    }

    public WorldObject(Position centerPosition, int size) {
        this(centerPosition, size, size);
    }

    public WorldObject(
            int xCoordinate,
            int yCoordinate,
            ObjectType objectType,
            int width,
            int height
    ) {
        this(new Position(xCoordinate, yCoordinate), objectType, width, height);
    }

    public WorldObject(
            int xCoordinate,
            int yCoordinate,
            ObjectType objectType,
            int size
    ) {
        this(xCoordinate, yCoordinate, objectType, size , size);
    }

    public WorldObject(
            int xCoordinate,
            int yCoordinate,
            int width,
            int height
    ) {
        this(xCoordinate, yCoordinate, ObjectType.OBSTACLE, width, height);
    }

    public WorldObject(int xCoordinate, int yCoordinate, int size) {
        this(xCoordinate, yCoordinate, size , size);
    }

    public Position getCenterPosition() {
        return this.centerPosition;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public int getHalfWidth() {
        switch (direction) {
            case EAST:
            case WEST:
                return height / 2;
            case NORTH:
            case SOUTH:
            default:
                return width / 2;
        }
    }

    public int getHalfHeight() {
        switch (direction) {
            case EAST:
            case WEST:
                return width / 2;
            case NORTH:
            case SOUTH:
            default:
                return height / 2;
        }
    }

    public int getWidth() {
        return getHalfWidth() * 2;
    }

    public int getHeight() {
        return getHalfHeight() * 2;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public int getCenterXCoordinate() {
        return centerPosition.getX();
    }

    public int getCenterYCoordinate() {
        return centerPosition.getY();
    }

    public int getMinimumXCoordinate() {
        return getCenterXCoordinate() - getHalfWidth();
    }

    public int getMaximumXCoordinate() {
        return getCenterXCoordinate() + getHalfWidth();
    }


    public int getMinimumYCoordinate() {
        return getCenterYCoordinate() - getHalfHeight();
    }

    public int getMaximumYCoordinate() {
        return getCenterYCoordinate() + getHalfHeight();
    }

    public Position getBottomLeftPosition() {
        return new Position(getMinimumXCoordinate(), getMinimumYCoordinate());
    }

    public Position getBottomRightPosition() {
        return new Position(getMaximumXCoordinate(), getMinimumYCoordinate());
    }

    public Position getTopRightPosition() {
        return new Position(getMaximumXCoordinate(), getMaximumYCoordinate());
    }

    public Position getTopLeftPosition() {
        return new Position(getMinimumXCoordinate(), getMaximumYCoordinate());
    }

    protected List<Integer> getXCoordinateRange() {
        List<Integer> xCoordinateRange = new ArrayList<>();
        for (
                int i = getMinimumXCoordinate();
                i <= getMaximumXCoordinate();
                i++
        ) {
            xCoordinateRange.add(i);
        }
        return xCoordinateRange;
    }

    protected List<Integer> getYCoordinateRange() {
        List<Integer> yCoordinateRange = new ArrayList<>();
        for (
                int i = getMinimumYCoordinate();
                i <= getMaximumYCoordinate();
                i++
        ) {
            yCoordinateRange.add(i);
        }
        return yCoordinateRange;
    }

    public List<Position> getPositionRange() {
        List<Position> positionRange = new ArrayList<>();
        List<Integer> xCoordinateRange = getXCoordinateRange();
        List<Integer> yCoordinateRange = getYCoordinateRange();
        for (int x : xCoordinateRange) {
            for (int y : yCoordinateRange) {
                positionRange.add(new Position(x, y));
            }
        }
        return positionRange;
    }

    public boolean containsPosition(Position p) {
        int x = p.getX();
        int y = p.getY();

        boolean withinTop = y <= getMaximumYCoordinate();
        boolean withinBottom = y >= getMinimumYCoordinate();
        boolean withinLeft = x >= getMinimumXCoordinate();
        boolean withinRight = x <= getMaximumXCoordinate();
        return withinTop && withinBottom && withinLeft && withinRight;
    }

    public boolean blocksPath(WorldObject o, Position endPosition) {
        if (!o.hasCollision() || !this.hasCollision) return false;

        int aX = o.getCenterXCoordinate();
        int aY = o.getCenterYCoordinate();
        int bX = endPosition.getX();
        int bY = endPosition.getY();

        WorldObject worldObject = o.createCopy();

        if (aX == bX) {
            for (int i = (Math.min(aY, bY)); i <= Math.max(aY, bY); i++) {
                worldObject.setCenterPosition(new Position(aX, i));
                if (this.overlapsWorldObject(worldObject)) {
                    return true;
                }
            }
        } else {
            for (int i = (Math.min(aX, bX)); i <= Math.max(aX, bX); i++) {
                worldObject.setCenterPosition(new Position(i, aY));
                if (this.overlapsWorldObject(worldObject)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean overlapsWorldObject(WorldObject o) {
        if (!o.hasCollision() || !this.hasCollision) return false;

        for (Position p : o.getPositionRange()) {
            if (containsPosition(p)) return true;
        }
        return false;
    }

    public void turnLeft() {
        direction = direction.getLeftDirection();
    }

    public void turnRight() {
        direction = direction.getRightDirection();
    }

    public void setCenterPosition(Position centerPosition) {
        this.centerPosition = centerPosition;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public void setObjectWidth(int objectWidth) {
        this.width = objectWidth;
    }

    public void setObjectHeight(int objectHeight) {
        this.height = objectHeight;
    }

    public void setCollision(boolean b) {
        hasCollision = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorldObject worldObject = (WorldObject) o;
        return centerPosition.equals(worldObject.getCenterPosition())
                && objectType.equals(worldObject.getObjectType())
                && direction.equals(worldObject.getDirection())
                && getWidth() == worldObject.getWidth()
                && getHeight() == worldObject.getHeight();
    }

    @Override
    public int hashCode() {
        return
                Objects.hash(
                        centerPosition,
                        direction,
                        objectType,
                        hasCollision,
                        width,
                        height
                );
    }

    public WorldObject createCopy() {
        WorldObject worldObject =
                new WorldObject(
                        centerPosition,
                        direction,
                        objectType,
                        width,
                        height
                );
        worldObject.setCollision(hasCollision);
        return worldObject;
    }
}
