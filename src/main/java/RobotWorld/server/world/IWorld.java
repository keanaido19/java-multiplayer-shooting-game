package RobotWorld.server.world;

import RobotWorld.Position;
import RobotWorld.robot.Robot;

import java.util.List;

public interface IWorld {

    /**
     * Enum that indicates response for updatePosition request
     */
    enum UpdateResponse {
        SUCCESS, //position was updated successfully
        FAILED_OUTSIDE_WORLD, //robot will go outside world limits if allowed, so it failed to update the position
        FAILED_OBSTRUCTED, //robot obstructed by at least one obstacle / other robot, thus cannot proceed.
    }
    /**
     * Enum that indicates response for fire request
     */
    enum FireResponse{
        MISS, // the bullet missed.
        HIT, // the bullet managed to hit an object in the world.
        OUT_OF_BULLETS, // there are no bullets to fire.
    }

    /***
     *
     * @param robot
     */
    void addPlayer(Robot robot);

    /***
     *
     * @param robot
     */
    void removeRobot(Robot robot);


    /***
     *
     * @param position
     * @return
     */
    boolean isPositionAllowed(Position position);

    /***
     *
     * @param a
     * @param b
     * @return
     */
    boolean isPathBlocked(Position a, Position b);


    /***
     *
     * @param position
     * @return
     */
    boolean isPositionBlocked(Position position);


    /***
     *
     * @return
     */
    List<Robot> getPlayers();


    /***
     *
     * @return
     */
    Position getRandomPosition();

    /***
     *
     * @param name
     * @return
     */
    boolean isRobotNameValid(String name);
}
