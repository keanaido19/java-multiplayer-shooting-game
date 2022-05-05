package RobotWorld.client.commands;


import RobotWorld.robot.Robot;

public abstract class Command {

    private final String name;
    private String argument;
    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }
    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }
    public static Command create(String commands) {
        String [] args = commands.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "forward":
                return new ForwardCommand(args[1]);
            case "left":
                return new LeftCommand();
            case "right":
                return new RightCommand();
            case "back" :
                return new BackCommand(args[1]);
            case "launch":
                return new LaunchCommand();
            case "fire":
                return new FireCommand();

            default:
                throw new IllegalArgumentException("Unsupported command: " + commands);

        }

    }
}