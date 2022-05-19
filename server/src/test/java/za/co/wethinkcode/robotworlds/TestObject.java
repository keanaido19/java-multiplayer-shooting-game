package za.co.wethinkcode.robotworlds;

import java.util.List;

public class TestObject {
    private final int id;
    private final String value;

    private List<Object> list;

    public TestObject(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
