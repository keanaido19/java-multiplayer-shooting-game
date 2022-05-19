package RobotWorld.robot;

import RobotWorld.Direction;
import RobotWorld.Position;
import RobotWorld.commands.Command;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Robot {
    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;
    private int numberOfShots;
    private int shield;

    public Robot(String name) {
        this.name = name;
        this.status = "NORMAL";
        this.numberOfShots = 0;
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

        this.position = new Position(newX, newY);
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

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
    public int getShots() {
        return numberOfShots;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }

    public boolean fireCommand() {
        return true;
    }

    public JSONObject setState() {
        JSONObject state = new JSONObject();
        JSONArray positions = new JSONArray();
        positions.add(position.getX());
        positions.add(position.getY());
        state.put("name", name);
        state.put("position", positions);
        state.put("direction",currentDirection);
        state.put("shots",numberOfShots);
        state.put("shield",shield);
        state.put("status",status);

        return state;
    }



    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {

        return this.status;}

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

    protected void setShots(int numberOfShots) {
        this.numberOfShots = numberOfShots;
    }
}
