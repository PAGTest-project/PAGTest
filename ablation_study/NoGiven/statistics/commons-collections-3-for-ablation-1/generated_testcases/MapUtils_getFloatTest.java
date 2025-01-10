
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getFloatTest {

    @Test
    public void testGetFloat() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 123.45f);
        map.put("key2", 678.90);
        map.put("key3", "notANumber");

        // Test case for when the value is a Float
        assertEquals(123.45f, MapUtils.getFloat(map, "key1"));

        // Test case for when the value is a Number but not a Float
        assertEquals(678.90f, MapUtils.getFloat(map, "key2"));

        // Test case for when the value is null
        assertNull(MapUtils.getFloat(map, "nonExistentKey"));

        // Test case for when the value is not a Number
        assertNull(MapUtils.getFloat(map, "key3"));
    }
}
