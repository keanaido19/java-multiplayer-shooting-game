package za.co.wethinkcode.robotworlds.jsonresponse.objects;

import za.co.wethinkcode.robotworlds.jsonresponse.enums.Direction;
import za.co.wethinkcode.robotworlds.jsonresponse.enums.Status;

import java.util.List;

public class RobotState {
    private List<Integer> position;
    private Direction direction;
    private int shields;
    private int shots;
    private Status status;

    public List<Integer> getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getShields() {
        return shields;
    }

    public int getShots() {
        return shots;
    }

    public Status getStatus() {
        return status;
    }
}
