package za.co.wethinkcode.robotworlds.commandhandler;

import org.junit.Test;
import za.co.wethinkcode.robotworlds.commands.ClientCommand;
import za.co.wethinkcode.robotworlds.commands.LaunchCommand;

import static org.junit.Assert.*;

public class ClientCommandHandlerTest {

    @Test
    public void getClientCommand() {
        Object o;
        String input;

        input = "launch hal sniper 5 5";
        o = ClientCommandHandler.getClientCommand(input);
        assertSame(o.getClass(), LaunchCommand.class);

        input = "left";
        o = ClientCommandHandler.getClientCommand(input);
        assertSame(o.getClass(), ClientCommand.class);

        input = "forward 10";
        o = ClientCommandHandler.getClientCommand(input);
        assertSame(o.getClass(), ClientCommand.class);

        input = "look";
        o = ClientCommandHandler.getClientCommand(input);
        assertSame(o.getClass(), ClientCommand.class);
    }
}