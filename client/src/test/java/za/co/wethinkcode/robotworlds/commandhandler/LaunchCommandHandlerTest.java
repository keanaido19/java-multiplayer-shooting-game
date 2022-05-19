package za.co.wethinkcode.robotworlds.commandhandler;

import org.junit.Test;
import za.co.wethinkcode.robotworlds.commands.LaunchCommand;

import static org.junit.Assert.*;

public class LaunchCommandHandlerTest {

    @Test
    public void checkCommand() {
        LaunchCommandHandler commandHandler = new LaunchCommandHandler();
        String[] arguments;

        arguments = new String[]{"hal", "sniper", "5", "5"};
        assertTrue(commandHandler.checkCommand("launch", arguments));

        arguments = new String[]{"pie", "default", "42", "21"};
        assertTrue(commandHandler.checkCommand("launch", arguments));

        arguments = new String[]{"lol", "lol", "1", "1"};
        assertTrue(commandHandler.checkCommand("launch", arguments));

        arguments = new String[]{"hal", "sniper", "5", "5"};
        assertFalse(commandHandler.checkCommand("lol", arguments));

        arguments = new String[]{"123", "sniper", "5", "5"};
        assertFalse(commandHandler.checkCommand("launch", arguments));

        arguments = new String[]{"hal", "sniper", "left"};
        assertFalse(commandHandler.checkCommand("launch", arguments));
    }

    @Test
    public void getCommand() {
        LaunchCommandHandler commandHandler = new LaunchCommandHandler();
        String[] arguments;
        Object o;

        arguments = new String[]{"hal", "sniper", "5", "5"};
        commandHandler.checkCommand("launch", arguments);

        o = commandHandler.getCommand();

        assertSame(o.getClass(), LaunchCommand.class);
    }
}