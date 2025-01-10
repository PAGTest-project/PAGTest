
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SingletonMap_putTest {

    @Test
    void testPutWithMatchingKey() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        String oldValue = map.put("key", "newValue");
        assertEquals("value", oldValue);
        assertEquals("newValue", map.getValue());
    }

    @Test
    void testPutWithNonMatchingKey() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            map.put("newKey", "newValue");
        });
        assertEquals("Cannot put new key/value pair - Map is fixed size singleton", exception.getMessage());
    }
}
