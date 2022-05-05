package RobotWorld.client.commands;

import RobotWorld.robot.Position;
import RobotWorld.robot.Robot;
import RobotWorld.world.SquareObstacle;

public class BackCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        SquareObstacle squareObstacle = new SquareObstacle(target.getPosition().getX(),target.getPosition().getY());
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(-nrSteps)) {
            target.setStatus("Moved back by " + nrSteps + " steps.");

        } else if(squareObstacle.blocksPath(target.getPosition(),new Position(target.getPosition().getX(),target.getPosition().getY()))){
            target.setStatus("Sorry, theres an obstacle in the way.");
        }else
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        return true;
    }

    public BackCommand(String argument) {

        super("back", argument);
    }
}
