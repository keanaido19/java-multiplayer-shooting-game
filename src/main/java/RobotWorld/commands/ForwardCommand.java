package RobotWorld.commands;

import RobotWorld.Position;
import RobotWorld.robot.Robot;
import RobotWorld.server.world.obstacle.SquareObstacle;


public class ForwardCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        SquareObstacle squareObstacle = new SquareObstacle(target.getPosition().getX(),target.getPosition().getY());
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(nrSteps)){
            target.setStatus("Moved forward by "+nrSteps+" steps.");

        } else if(squareObstacle.blocksPath(target.getPosition(),new Position(target.getPosition().getX(),target.getPosition().getY()))){
            target.setStatus("Sorry, theres an obstacle in the way.");
        }else
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        return true;
    }

    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}
