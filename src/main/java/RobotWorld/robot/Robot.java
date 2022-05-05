package RobotWorld.robot;

import RobotWorld.client.commands.Command;

import java.util.List;

public class Robot {

    private final Position TOP_LEFT = new Position(-100, 200);
    private final Position BOTTOM_RIGHT = new Position(100, -200);
    public static final Position CENTRE = new Position(0, 0);
    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;
    private int numberOfShots;
    private int distanceOfShots;
    public Robot(String name) {
        this.position = CENTRE;
        this.currentDirection = Direction.north;
        this.name = name;
        this.status = "Ready";
        this.numberOfShots =0;
        this.distanceOfShots = 0;
    }

    public boolean updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.north.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        if (Direction.east.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }
        if (Direction.west.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        if (Direction.south.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;
        }
        return true;
    }
    public void turnRight() {

        switch (this.currentDirection) {
            case north:
                this.currentDirection = Direction.east;
                break;
            case south:
                this.currentDirection = Direction.west;
                break;
            case east:
                this.currentDirection = Direction.south;
                break;
            case west:
                this.currentDirection = Direction.north;
                break;
            default:
                throw new IllegalStateException("has no opposite.");
        }
    }
    public void turnLeft() {
        switch (this.currentDirection) {
            case north:
                this.currentDirection = Direction.west;
                break;
            case south:
                this.currentDirection = Direction.east;
                break;
            case east:
                this.currentDirection = Direction.north;
                break;
            case west:
                this.currentDirection = Direction.south;
                break;
            default:
                throw new IllegalStateException(" has no opposite.");
        }
    }
    public boolean handleCommand(Command command) {

        return command.execute(this);
    }
    public boolean fireCommand(){
        return true;
    }

    public String toString() {
        return status;


    }
    public void setStatus(String status) {
        if (position != null) {
            this.status = "[" + position.getX() + "," + position.getY() + "] " + getName() + " : " + status;
        } else {
            this.status = "> " + getName() + " : " + status;
        }

    }

    public String getName() {

        return name;
    }
    public Position getPosition() {
        return this.position;
    }
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }
}
