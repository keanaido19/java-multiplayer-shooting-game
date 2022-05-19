package RobotWorld.server.world;

import RobotWorld.Position;
import RobotWorld.robot.Robot;
import RobotWorld.server.world.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World implements IWorld{
    private final Position TOP_LEFT;
    private final Position BOTTOM_RIGHT;
    private final Config worldConfig;
    private final List<Robot> players = new ArrayList<>();
    private final List<Obstacle> obstacles = new ArrayList<>();;

    public World(){
        this.worldConfig = new DefaultConfig();
        this.TOP_LEFT = worldConfig.topLeft();
        this.BOTTOM_RIGHT = worldConfig.bottomRight();
    }

    public World(Config config)
    {
        this.worldConfig = config;
        this.TOP_LEFT = worldConfig.topLeft();
        this.BOTTOM_RIGHT = worldConfig.bottomRight();
    }

    public void addPlayer(Robot robot)
    {
        if(robot.getShield() > this.worldConfig.getMaximumShield())
            robot.setShield(worldConfig.getMaximumShield());

        players.add(robot);
    }

    public void removeRobot(Robot robot){
        players.remove(robot);
    }

    public boolean isRobotNameValid(String name){
        return players.stream().noneMatch(robot -> robot.getName().equals(name));
    }

    public boolean isPositionAllowed(Position position){
        return position.isIn(this.TOP_LEFT, this.BOTTOM_RIGHT);
    }

    public boolean isPathBlocked(Position a, Position b)
    {
        return obstacles.stream().noneMatch(obs -> obs.blocksPath(a,b)) &&
                players.stream().noneMatch(target -> target.blocksPath(a,b));
    }

    public boolean isPositionBlocked(Position position)
    {
        return obstacles.stream().noneMatch(obs -> obs.blocksPosition(position))
                && players.stream().noneMatch(robot ->
                robot.blocksPosition(position));
    }

    public List<Robot> getPlayers() {
        return players;
    }

    public Position getRandomPosition()
    {
        Random random = new Random();
        int x = random.nextInt(worldConfig.getWidth()) - worldConfig.getWidth()/2;
        int y = random.nextInt(worldConfig.getHeight()) - worldConfig.getHeight()/2;
        while (isPositionBlocked(new Position(x,y)))
        {
            x = random.nextInt(worldConfig.getWidth()) - worldConfig.getWidth()/2;
            y = random.nextInt(worldConfig.getHeight()) - worldConfig.getHeight()/2;
        }
        return new Position(x,y);
    }

    @Override
    public String toString() {
        return "World{" +
                "players=" + players +
                ", obstacles=" + obstacles +
                '}';
    }
}
