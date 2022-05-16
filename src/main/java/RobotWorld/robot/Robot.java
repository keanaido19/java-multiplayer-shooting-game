package RobotWorld.robot;

import RobotWorld.Direction;
import RobotWorld.Position;
import RobotWorld.commands.Command;

public class Robot {
    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;
    private int numberOfShots;
    private int shield;
    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.numberOfShots =0;
        this.shield = 0;
    }



    public boolean updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }
        if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        return true;
    }
    public void turnRight() {

        switch (this.currentDirection) {
            case NORTH:
                this.currentDirection = Direction.EAST;
                break;
            case SOUTH:
                this.currentDirection = Direction.WEST;
                break;
            case EAST:
                this.currentDirection = Direction.SOUTH;
                break;
            case WEST:
                this.currentDirection = Direction.NORTH;
                break;
            default:
                throw new IllegalStateException("has no opposite.");
        }
    }
    public void turnLeft() {
        switch (this.currentDirection) {
            case NORTH:
                this.currentDirection = Direction.WEST;
                break;
            case SOUTH:
                this.currentDirection = Direction.EAST;
                break;
            case EAST:
                this.currentDirection = Direction.NORTH;
                break;
            case WEST:
                this.currentDirection = Direction.SOUTH;
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

    @Override
    public String toString() {
        return "state {" +
                ", name='" + name + '\'' +
                "position=" + position +
                ", direction=" + currentDirection +
                ", shots:" + numberOfShots +
                ", shield: " + shield +
                ", status: " + status + '\'' +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {

        return name;
    }

    public boolean blocksPosition(Position position)
    {
        return this.position.getX() == position.getX()
                && this.position.getY() <= position.getY();
    }

    public boolean blocksPath(Position a, Position b)
    {
        int y = this.position.getY();
        int x = this.position.getX();
        int maxX = Math.max(a.getX(),b.getX()), minX = Math.min(a.getX(),b.getX());
        int maxY = Math.max(a.getY(),b.getY()), minY = Math.min(a.getY(),b.getY());

        return minY <= y&&y <= maxY && minX<= x && x <= maxX;
    }
    public Position getPosition() {
        return this.position;
    }
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }
}
