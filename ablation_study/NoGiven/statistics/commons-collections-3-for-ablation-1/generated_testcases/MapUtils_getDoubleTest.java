
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getDoubleTest {

    @Test
    public void testGetDouble() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 123.45);
        map.put("key2", 678);
        map.put("key3", "999.99");

        // Test case for direct Double value
        assertEquals(123.45, MapUtils.getDouble(map, "key1"));

        // Test case for non-Double Number value
        assertEquals(678.0, MapUtils.getDouble(map, "key2"));

        // Test case for null value
        assertNull(MapUtils.getDouble(map, "nonExistentKey"));
    }
}
