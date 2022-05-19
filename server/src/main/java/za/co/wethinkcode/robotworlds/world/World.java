package za.co.wethinkcode.robotworlds.world;

import za.co.wethinkcode.robotworlds.world.data.WorldData;
import za.co.wethinkcode.robotworlds.world.enums.ObjectType;
import za.co.wethinkcode.robotworlds.world.enums.UpdateResponse;
import za.co.wethinkcode.robotworlds.world.objects.WorldObject;
import za.co.wethinkcode.robotworlds.world.objects.obstacles.Boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static za.co.wethinkcode.robotworlds.world.enums.ObjectType.EDGE;

public class World {

    private final Random random = new Random();
    private final WorldData worldData;
    private final Boundary worldBoundary;

    private final List<WorldObject> worldObjects = new ArrayList<>();

    public World(WorldData worldData) {
        this.worldData = worldData;
        this.worldBoundary =
                new Boundary(
                        worldData.getWidth() + 2,
                        worldData.getHeight() + 2
                );
    }

    public WorldData getWorldData() {
        return worldData;
    }

    public Boundary getWorldBoundary() {
        return worldBoundary;
    }

    public List<WorldObject> getWorldObjects(ObjectType o) {
        List<WorldObject> returnList = new ArrayList<>();
        if (EDGE.equals(o))
            returnList.add(worldBoundary);
        else {
            for (WorldObject worldObject : worldObjects) {
                if (worldObject.getObjectType().equals(o))
                    returnList.add(worldObject);
            }
        }
        return returnList;
    }

    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }

    private int getRandomXCoordinate() {
        return
                random.nextInt(
                        worldBoundary.getMaximumXCoordinate()
                                - worldBoundary.getMinimumXCoordinate()
                ) + worldBoundary.getMinimumXCoordinate();
    }

    private int getRandomYCoordinate() {
        return
                random.nextInt(
                        worldBoundary.getMaximumYCoordinate()
                                - worldBoundary.getMinimumYCoordinate()
                ) + worldBoundary.getMinimumYCoordinate();
    }

    public boolean isSpaceAvailableForWorldObject(WorldObject o) {
        if (!worldBoundary.isPositionInsideBoundary(o.getCenterPosition())
                || worldBoundary.overlapsWorldObject(o)
        ) return false;

        for (WorldObject worldObject : worldObjects) {
            if (!worldObject.equals(o) && worldObject.overlapsWorldObject(o))
                return false;
        }

        return true;
    }

    public Position getUnoccupiedPosition(int width, int height) {
        Position p;
        WorldObject o;
        int counter = 0;
        while (true) {
            if (counter == 10000) return null;
            p = new Position(getRandomXCoordinate(), getRandomYCoordinate());
            o = new WorldObject(p, width, height);
            if (isSpaceAvailableForWorldObject(o)) return p;
            counter++;
        }
    }

    public Position getUnoccupiedPosition(int objectSize) {
        return getUnoccupiedPosition(objectSize, objectSize);
    }

    public void addObjectToWorld(WorldObject o) {
        if (isSpaceAvailableForWorldObject(o)) worldObjects.add(o);
    }

    public void removeObjectFromWorld(WorldObject o) {
        worldObjects.remove(o);
    }

    public boolean isObjectInWorld(WorldObject o) {
        return worldObjects.contains(o);
    }

    private boolean canWorldObjectMoveInWorld(
            WorldObject o,
            Position newPosition
    ) {

        if (worldBoundary.blocksPath(o, newPosition))
            return false;

        for (WorldObject worldObject : worldObjects) {
            if (!worldObject.equals(o)
                    && worldObject.blocksPath(o, newPosition)
            ) return false;
        }

        return true;
    }

    public UpdateResponse moveWorldObject(WorldObject o, Position newPosition) {
        if (!isObjectInWorld(o))
            return UpdateResponse.FAILED_NOT_IN_WORLD;
        if (!canWorldObjectMoveInWorld(o, newPosition))
            return UpdateResponse.FAILED_OBSTRUCTED;
        o.setCenterPosition(newPosition);
        return UpdateResponse.SUCCESS;
    }
}
