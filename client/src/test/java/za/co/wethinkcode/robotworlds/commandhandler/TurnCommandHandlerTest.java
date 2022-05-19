package za.co.wethinkcode.robotworlds.commandhandler;

import org.junit.Test;
import za.co.wethinkcode.robotworlds.commands.ClientCommand;
import za.co.wethinkcode.robotworlds.commands.LaunchCommand;

import static org.junit.Assert.*;

public class TurnCommandHandlerTest {

    @Test
    public void checkCommand() {
        TurnCommandHandler commandHandler = new TurnCommandHandler();
        String[] arguments;

        arguments = new String[]{};
        assertTrue(commandHandler.checkCommand("left", arguments));

        arguments = new String[]{};
        assertTrue(commandHandler.checkCommand("right", arguments));

        arguments = new String[]{};
        assertFalse(commandHandler.checkCommand("lol", arguments));

        arguments = new String[]{"1", "2", "3", "4"};
        assertFalse(commandHandler.checkCommand("left", arguments));

        arguments = new String[]{"lol"};
        assertFalse(commandHandler.checkCommand("right", arguments));
    }

    @Test
    public void getCommand() {
        TurnCommandHandler commandHandler = new TurnCommandHandler();
        String[] arguments;
        Object o;

        arguments = new String[]{};
        commandHandler.checkCommand("left", arguments);

        o = commandHandler.getCommand();

        assertSame(o.getClass(), ClientCommand.class);
    }
}