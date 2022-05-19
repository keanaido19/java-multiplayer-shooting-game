package za.co.wethinkcode.robotworlds;

import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.*;
import static za.co.wethinkcode.robotworlds.Helpers.checkValidIpAndPort;

public class HelpersTest {

    @Test
    public void testCheckValidIpAndPort() {
        assertFalse(checkValidIpAndPort(new String[] {""}));
        assertFalse(checkValidIpAndPort(new String[] {"lol"}));
        assertFalse(checkValidIpAndPort(new String[] {"1.2"}));
        assertFalse(checkValidIpAndPort(new String[] {"lol", "5000"}));
        assertFalse(checkValidIpAndPort(new String[] {"-1.1.1.1", "5000"}));
        assertFalse(checkValidIpAndPort(new String[] {"1,1a1{1", "5000"}));
        assertFalse(checkValidIpAndPort(new String[] {"127,0,0,1", "5000"}));
        assertFalse(checkValidIpAndPort(new String[] {"localhost", "65536"}));
        assertFalse(checkValidIpAndPort(new String[] {"255.255.255.256",
                "2000"}));
        assertFalse(checkValidIpAndPort(new String[] {"localhost", "5000",
                "lol"}));
        assertFalse(checkValidIpAndPort(new String[] {"255.255.255.255.255",
        "5000"}));
        assertFalse(checkValidIpAndPort(new String[] {"255.255.255.255",
                "sdf"}));
        assertFalse(checkValidIpAndPort(new String[] {"!@#4.234.22.1",
                "2@#$"}));

        assertTrue(checkValidIpAndPort(new String[] {"localhost", "5000"}));
        assertTrue(checkValidIpAndPort(new String[] {"127.0.0.1", "12345"}));
        assertTrue(checkValidIpAndPort(new String[] {"0.0.0.0", "0"}));
        assertTrue(checkValidIpAndPort(new String[] {"12.236.2.45", "65535"}));
        assertTrue(checkValidIpAndPort(new String[] {"123.236.0.45", "64999"}));
        assertTrue(checkValidIpAndPort(new String[] {"8.8.8.8", "50342"}));
        assertTrue(checkValidIpAndPort(new String[] {"196.44.123.12", "8888"}));
    }

    @Test
    public void isPositiveInteger() {
        assertFalse(Helpers.isPositiveInteger(""));
        assertFalse(Helpers.isPositiveInteger("-1"));
        assertFalse(Helpers.isPositiveInteger("-12323"));
        assertFalse(Helpers.isPositiveInteger("2147483648"));
        assertFalse(Helpers.isPositiveInteger("dksjfhsak"));
        assertFalse(Helpers.isPositiveInteger("1.2"));

        assertTrue(Helpers.isPositiveInteger("0"));
        assertTrue(Helpers.isPositiveInteger("1"));
        assertTrue(Helpers.isPositiveInteger("974398213"));
        assertTrue(Helpers.isPositiveInteger("2147483647"));
    }

    @Test
    public void createClientResponse() {
        JsonObject jsonObject;

        jsonObject = Helpers.createClientResponse(
                "testBot",
                "testCommand",
                new String[] {"test1", "test2", "test3"}
        );
        assertEquals(
                "{\"robot\":\"testBot\",\"command\":\"testCommand\"," +
                        "\"arguments\":[\"test1\",\"test2\",\"test3\"]}",
                jsonObject.toString()
        );

        jsonObject = Helpers.createClientResponse(
                "testBot",
                "testCommand",
                new String[] {}
        );
        assertEquals(
                "{\"robot\":\"testBot\",\"command\":\"testCommand\"," +
                        "\"arguments\":[]}",
                jsonObject.toString()
        );

        jsonObject = Helpers.createClientResponse(
                "testBot",
                "testCommand",
                new String[] {"test1", "1", "2"}
        );
        assertEquals(
                "{\"robot\":\"testBot\",\"command\":\"testCommand\"," +
                        "\"arguments\":[\"test1\",1,2]}",
                jsonObject.toString()
        );

    }
}