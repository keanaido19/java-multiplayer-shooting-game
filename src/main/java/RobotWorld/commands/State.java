package RobotWorld.commands;

import RobotWorld.robot.Robot;

public class State extends Command {
    @Override
    public boolean execute(Robot target) {
        return false;
    }

    public State(String name) {
        super("state");
    }
}
