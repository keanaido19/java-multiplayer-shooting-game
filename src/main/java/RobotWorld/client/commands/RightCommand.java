package RobotWorld.client.commands;

import RobotWorld.robot.Robot;

public class RightCommand extends Command {
    public RightCommand() {
        super("right");
    }

    @Override
    public boolean execute(Robot target) {
        target.turnRight();
        target.setStatus("turned right");

        return false;
    }
}
