
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getLongTest {

    @Test
    public void testGetLong() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 123L);
        map.put("key2", 456);
        map.put("key3", "789");

        // Test case for Long value
        assertEquals(123L, MapUtils.getLong(map, "key1"));

        // Test case for Integer value
        assertEquals(456L, MapUtils.getLong(map, "key2"));

        // Test case for null value
        assertNull(MapUtils.getLong(map, "key4"));
    }
}
