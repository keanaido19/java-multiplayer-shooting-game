package za.co.wethinkcode.robotworlds.world.data;

import za.co.wethinkcode.robotworlds.world.Position;
import za.co.wethinkcode.robotworlds.world.enums.Direction;
import za.co.wethinkcode.robotworlds.world.enums.ObjectType;
import za.co.wethinkcode.robotworlds.world.enums.Status;
import za.co.wethinkcode.robotworlds.world.objects.WorldObject;

import java.util.Objects;

public class WorldObjectData {
    Position position;
    Direction direction;
    ObjectType type;
    Status status = Status.NORMAL;
    int width;
    int height;

    public WorldObjectData(WorldObject o) {
        position = o.getCenterPosition();
        direction = o.getDirection();
        type = o.getObjectType();
        width = o.getWidth();
        height = o.getHeight();
    }

    public WorldObjectData(WorldObject o, Status status) {
        position = o.getCenterPosition();
        direction = o.getDirection();
        type = o.getObjectType();
        this.status = status;
        width = o.getWidth();
        height = o.getHeight();
    }

    public WorldObjectData(
            Position position,
            Direction direction,
            ObjectType type,
            int width,
            int height
    ) {
        this.position = position;
        this.direction = direction;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorldObjectData)) return false;
        WorldObjectData that = (WorldObjectData) o;
        return width == that.width
                && height == that.height
                && position.equals(that.position)
                && direction == that.direction
                && status == that.status
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction, type, status, width, height);
    }
}
