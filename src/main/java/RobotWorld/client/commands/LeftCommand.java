package RobotWorld.client.commands;

import RobotWorld.robot.Robot;

public class LeftCommand extends Command{

    public LeftCommand() {

        super("left");
    }

    @Override
    public boolean execute(Robot target) {
        target.turnLeft();
        target.setStatus("turned left");

        return false;
    }
}

