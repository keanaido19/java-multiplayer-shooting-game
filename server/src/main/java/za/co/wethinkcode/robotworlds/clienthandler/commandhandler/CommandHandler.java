package za.co.wethinkcode.robotworlds.clienthandler.commandhandler;

import za.co.wethinkcode.robotworlds.clienthandler.commandhandler.auxiliarycommandhandler.AuxiliaryCommandHandler;
import za.co.wethinkcode.robotworlds.clienthandler.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private final List<CommandHandlerStrategy> commandHandlerStrategies;
    private static final List<Class<? extends CommandHandlerStrategy>>
            commandHandlerStrategyClasses =
            List.of(
                    LaunchCommandHandler.class,
                    AuxiliaryCommandHandler.class,
                    MovementCommandHandler.class,
                    TurnCommandHandler.class
            );
    private static final Class<?>[] classArguments =
            new Class[]{String.class, String.class, List.class};

    public CommandHandler(
            String robotName,
            String command,
            List<Object> commandArguments
    ) {
        this.commandHandlerStrategies =
                getCommandHandlerStrategies(
                        robotName,
                        command,
                        commandArguments
                );
    }

    private static List<CommandHandlerStrategy> getCommandHandlerStrategies(
            String robotName,
            String command,
            List<Object> commandArguments
    ) {
        List<CommandHandlerStrategy> commandHandlerStrategies =
                new ArrayList<>() {};

        for (Class<? extends CommandHandlerStrategy> commandHandlerStrategyClass
                : commandHandlerStrategyClasses
        ) {
            try {
                CommandHandlerStrategy commandHandlerStrategy =
                        commandHandlerStrategyClass
                                .getDeclaredConstructor(
                                        classArguments
                                ).newInstance(
                                        robotName,
                                        command,
                                        commandArguments
                                );
                commandHandlerStrategies.add(commandHandlerStrategy);
            } catch (
                    NoSuchMethodException |
                    IllegalAccessException |
                    InstantiationException |
                    InvocationTargetException ignored
            ) {
            }
        }

        return commandHandlerStrategies;
    }

    public boolean isValidCommand() {
        for (CommandHandlerStrategy commandHandlerStrategy :
                commandHandlerStrategies
        ) {
            if (commandHandlerStrategy.isCommandValid()) return true;
        }
        return false;
    }

    public boolean isValidCommandArguments() {
        for (CommandHandlerStrategy commandHandlerStrategy :
                commandHandlerStrategies
        ) {
            if (commandHandlerStrategy.isCommandValid()) {
                return commandHandlerStrategy.isCommandArgumentsValid();
            }
        }
        return false;
    }

    public Command getCommand() {
        for (CommandHandlerStrategy commandHandlerStrategy :
                commandHandlerStrategies
        ) {
            if (commandHandlerStrategy.isCommandValid() &&
                    commandHandlerStrategy.isCommandArgumentsValid()
            ) {
                return commandHandlerStrategy.getCommand();
            }
        }
        return null;
    }
}
