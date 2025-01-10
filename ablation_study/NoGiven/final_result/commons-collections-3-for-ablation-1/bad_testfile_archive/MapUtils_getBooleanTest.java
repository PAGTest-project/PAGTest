
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MapUtils_getBooleanTest {

    @Test
    public void testGetBoolean() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", true);
        map.put("key2", "true");
        map.put("key3", 1);
        map.put("key4", 0);
        map.put("key5", "invalid");

        // Test Boolean instance
        assertEquals(Boolean.TRUE, MapUtils.getBoolean(map, "key1"));

        // Test String instance
        assertEquals(Boolean.TRUE, MapUtils.getBoolean(map, "key2"));

        // Test Number instance (non-zero)
        assertEquals(Boolean.TRUE, MapUtils.getBoolean(map, "key3"));

        // Test Number instance (zero)
        assertEquals(Boolean.FALSE, MapUtils.getBoolean(map, "key4"));

        // Test null answer
        assertNull(MapUtils.getBoolean(map, "nonExistentKey"));

        // Test invalid type
        assertNull(MapUtils.getBoolean(map, "key5"));

        // Test null map
        assertNull(MapUtils.getBoolean(null, "key1"));
    }
}
