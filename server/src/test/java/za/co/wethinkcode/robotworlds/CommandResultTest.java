package za.co.wethinkcode.robotworlds;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotworlds.clienthandler.commands.CommandResult;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandResultTest {
    @Test
    void CommandResultEnum(){
        CommandResult[] d = CommandResult.values();
        assertEquals(3,d.length);

    }
    @Test
    void ShouldSeeCommandResult(){
        CommandResult[] d = CommandResult.values();
        assertEquals("OK", String.valueOf(d[0]));
    }
    @Test
    void isNotEmptyCommandResult(){
        CommandResult[] d = CommandResult.values();
        assertNotNull(d);
    }
}
