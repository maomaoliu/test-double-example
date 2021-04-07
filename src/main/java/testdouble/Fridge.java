package testdouble;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Fridge {
    protected Map<String, Object> map = new ConcurrentHashMap<>();
    protected final int MAX_CAPACITY = 2;

    public void open() {
        // open the door
    }

    public void put(String name, Object something) throws FridgeFullException {
        if (!isFull()) {
            map.put(name, something);
        } else {
            throw new FridgeFullException();
        }
    }

    public Object get(String name) throws ObjectIsNotInException {
        if (map.containsKey(name)) return map.remove(name);
        throw new ObjectIsNotInException();
    }

    public void close() {
        // close the door
    }

    public boolean isFull() {
        return map.size() >= MAX_CAPACITY;
    }
}
