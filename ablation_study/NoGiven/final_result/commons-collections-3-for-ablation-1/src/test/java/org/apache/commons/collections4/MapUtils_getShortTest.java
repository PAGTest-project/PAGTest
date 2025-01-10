
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getShortTest {

    @Test
    public void testGetShort() {
        Map<String, Number> map = new HashMap<>();
        map.put("key1", (short) 123);
        map.put("key2", 456);

        // Test case 1: Key exists and value is of type Short
        Short result1 = MapUtils.getShort(map, "key1");
        assertEquals((short) 123, result1);

        // Test case 2: Key exists and value is not of type Short
        Short result2 = MapUtils.getShort(map, "key2");
        assertEquals((short) 456, result2);

        // Test case 3: Key does not exist
        Short result3 = MapUtils.getShort(map, "key3");
        assertNull(result3);
    }
}
