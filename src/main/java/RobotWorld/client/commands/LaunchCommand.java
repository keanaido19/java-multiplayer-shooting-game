package RobotWorld.client.commands;

import RobotWorld.robot.Robot;

public class LaunchCommand extends Command{
    public LaunchCommand() {
        super("launch");
    }

    @Override
    public boolean execute(Robot target) {
        return false;
    }
}
