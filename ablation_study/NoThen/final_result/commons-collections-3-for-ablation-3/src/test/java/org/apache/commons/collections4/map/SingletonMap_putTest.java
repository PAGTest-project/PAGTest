
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SingletonMap_putTest {

    @Test
    public void testPutWithMatchingKey() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "oldValue");
        String oldValue = map.put("key", "newValue");
        assertEquals("oldValue", oldValue);
        assertEquals("newValue", map.get("key"));
    }

    @Test
    public void testPutWithNonMatchingKey() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            map.put("newKey", "newValue");
        });
        assertEquals("Cannot put new key/value pair - Map is fixed size singleton", exception.getMessage());
    }
}
