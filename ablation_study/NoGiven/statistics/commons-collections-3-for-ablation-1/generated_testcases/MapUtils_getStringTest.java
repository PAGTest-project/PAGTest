
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getStringTest {

    @Test
    public void testGetString_withNonNullMapAndKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        String result = MapUtils.getString(map, "key1");
        assertEquals("value1", result);
    }

    @Test
    public void testGetString_withNonNullMapAndNullKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        String result = MapUtils.getString(map, null);
        assertNull(result);
    }

    @Test
    public void testGetString_withNullMap() {
        String result = MapUtils.getString(null, "key1");
        assertNull(result);
    }
}
