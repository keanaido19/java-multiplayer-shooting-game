package za.co.wethinkcode.robotworlds;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class JsonHandlerTest {

    @Test
    public void createJsonObject() {
        JsonObject jsonObject;
        jsonObject =
                JsonHandler
                        .createJsonObject(
                                "{\"id\":1,\"value\":\"" +
                                        "this is just a test\"}"
                        );
        assertEquals(
                "{\"id\":1,\"value\":\"this is just a test\"}",
                jsonObject.toString()
        );
        assertSame(jsonObject.get("id").getClass(), JsonPrimitive.class);

        jsonObject =
                JsonHandler
                        .createJsonObject(
                                "{\"id\":[1,\"a\"],\"value\":\"" +
                                        "this is just a test\"}"
                        );
        assertEquals(
                "{\"id\":[1,\"a\"],\"value\":\"this is just a test\"}",
                jsonObject.toString()
        );
        assertSame(jsonObject.get("id").getClass(), JsonArray.class);

    }

    @Test
    public void convertJsonArrayToList() {
        JsonArray jsonArray;
        Object o;
        jsonArray = new JsonArray();
        jsonArray.add("test");
        jsonArray.add(1);
        o = JsonHandler.convertJsonArrayToList(jsonArray);
        assertSame(o.getClass(), ArrayList.class);
        assertEquals("test", ((ArrayList<?>) o).get(0));
        assertEquals(1.0, ((ArrayList<?>) o).get(1));

        jsonArray = new JsonArray();
        jsonArray.add(true);
        jsonArray.add("true");
        o = JsonHandler.convertJsonArrayToList(jsonArray);
        assertSame(o.getClass(), ArrayList.class);
        assertSame(Boolean.class, ((ArrayList<?>) o).get(0).getClass());
        assertTrue((Boolean) ((ArrayList<?>) o).get(0));
        assertEquals("true", ((ArrayList<?>) o).get(1));
    }

    @Test
    public void convertJsonObjectToList() {
        JsonObject jsonObject;
        JsonArray jsonArray;
        Object o;
        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("value", "this is a test");
        o = JsonHandler.convertJsonObjectToList(jsonObject);
        assertSame(Collections.singletonList("").getClass(), o.getClass());
        assertEquals("[{id=1.0, value=this is a test}]", o.toString());

        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 2);
        jsonArray = new JsonArray();
        jsonArray.add("this");
        jsonArray.add("is");
        jsonArray.add("a");
        jsonArray.add("test");
        jsonArray.add(91);
        jsonObject.add("value", jsonArray);
        o = JsonHandler.convertJsonObjectToList(jsonObject);
        assertSame(Collections.singletonList("").getClass(), o.getClass());
        assertEquals(
                "[{id=2.0, value=[this, is, a, test, 91.0]}]",
                o.toString()
        );
    }

    @Test
    public void convertJsonObjectToHashMap() {
        JsonObject jsonObject;
        JsonArray jsonArray;
        Object o;
        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("value", "this is a test");
        o = JsonHandler.convertJsonObjectToHashMap(jsonObject);
        assertSame(LinkedHashMap.class, o.getClass());
        assertEquals("{id=1.0, value=this is a test}", o.toString());
        assertEquals(1.0, ((LinkedHashMap<?, ?>) o).get("id"));
        assertEquals(
                "this is a test",
                ((LinkedHashMap<?, ?>) o).get("value")
        );

        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 2);
        jsonArray = new JsonArray();
        jsonArray.add("this");
        jsonArray.add("is");
        jsonArray.add("a");
        jsonArray.add("test");
        jsonArray.add(91);
        jsonObject.add("value", jsonArray);
        o = JsonHandler.convertJsonObjectToHashMap(jsonObject);
        assertSame(LinkedHashMap.class, o.getClass());
        assertEquals(
                "{id=2.0, value=[this, is, a, test, 91.0]}",
                o.toString()
        );
        assertEquals(2.0, ((LinkedHashMap<?, ?>) o).get("id"));
        assertEquals(
                List.of("this", "is", "a", "test", 91.0),
                ((LinkedHashMap<?, ?>) o).get("value")
        );
    }

    @Test
    public void getJsonValueAsList() {
        JsonObject jsonObject;
        JsonArray jsonArray;
        Object o;
        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("value", "this is a test");
        o = JsonHandler.getJsonValueAsList("id", jsonObject);
        assertSame(ArrayList.class, o.getClass());
        assertEquals("[1]", o.toString());
        o = JsonHandler.getJsonValueAsList("value", jsonObject);
        assertEquals("[this is a test]", o.toString());

        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 2);
        jsonArray = new JsonArray();
        jsonArray.add("this");
        jsonArray.add("is");
        jsonArray.add("a");
        jsonArray.add("test");
        jsonArray.add(91);
        jsonObject.add("value", jsonArray);
        o = JsonHandler.getJsonValueAsList("id", jsonObject);
        assertSame(ArrayList.class, o.getClass());
        assertEquals("[2]", o.toString());
        o = JsonHandler.getJsonValueAsList("value", jsonObject);
        assertEquals("[this, is, a, test, 91.0]", o.toString());
    }

    @Test
    public void getJsonValueAsString() {
        JsonObject jsonObject;
        JsonArray jsonArray;
        Object o;
        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("value", "this is a test");
        o = JsonHandler.getJsonValueAsString("id", jsonObject);
        assertSame(String.class, o.getClass());
        assertEquals("1", o.toString());
        o = JsonHandler.getJsonValueAsString("value", jsonObject);
        assertEquals("this is a test", o.toString());
        o = JsonHandler.getJsonValueAsString("lol", jsonObject);
        assertNull(o);
    }

    @Test
    public void convertJavaObjectToJsonString() {
        TestObject testObject;
        Object o;

        testObject = new TestObject(1, "this is a test");
        o = JsonHandler.convertJavaObjectToJsonString(testObject);
        assertSame(String.class, o.getClass());
        assertEquals(
                "{\"id\":1,\"value\":\"this is a test\"}",
                o.toString()
        );

        testObject = new TestObject(1, "2");
        testObject.setList(List.of(3, "four", new String[] {"lol", "loll"}));
        o = JsonHandler.convertJavaObjectToJsonString(testObject);
        assertSame(String.class, o.getClass());
        assertEquals(
                "{\"id\":1,\"value\":\"2\",\"list\":" +
                        "[3,\"four\",[\"lol\",\"loll\"]]}",
                o.toString()
        );
    }
}