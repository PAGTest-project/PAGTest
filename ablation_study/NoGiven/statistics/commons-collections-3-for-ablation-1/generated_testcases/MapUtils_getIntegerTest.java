
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MapUtils_getIntegerTest {

    @Test
    void testGetInteger() {
        Map<String, Number> map = new HashMap<>();
        map.put("key1", 42);
        map.put("key2", 3.14);

        // Test case for when the value is an Integer
        assertEquals(42, MapUtils.getInteger(map, "key1"));

        // Test case for when the value is not an Integer but can be converted to one
        assertEquals(3, MapUtils.getInteger(map, "key2"));

        // Test case for when the value is null
        assertNull(MapUtils.getInteger(map, "key3"));
    }
}
