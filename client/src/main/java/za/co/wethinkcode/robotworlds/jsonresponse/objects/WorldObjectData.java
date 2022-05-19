package za.co.wethinkcode.robotworlds.jsonresponse.objects;

import za.co.wethinkcode.robotworlds.jsonresponse.enums.Direction;
import za.co.wethinkcode.robotworlds.jsonresponse.enums.ObjectType;
import za.co.wethinkcode.robotworlds.jsonresponse.enums.Status;

public class WorldObjectData {
    Position position;
    Direction direction;
    ObjectType type;
    Status status;
    int width;
    int height;

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Status getStatus() {
        return status;
    }

    public ObjectType getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
