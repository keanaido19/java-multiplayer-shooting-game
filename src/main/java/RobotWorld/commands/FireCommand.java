package RobotWorld.commands;

import RobotWorld.robot.Robot;

public class FireCommand extends Command{
    public FireCommand() {
        super("fire");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("You got SHOT!!");

        return false;
    }
}
