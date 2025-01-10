
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MapUtils_safeAddToMapTest {

    @Test
    public void testSafeAddToMap_Success() {
        Map<String, Object> map = new HashMap<>();
        String key = "testKey";
        String value = "testValue";

        MapUtils.safeAddToMap(map, key, value);

        assertEquals(value, map.get(key));
    }

    @Test
    public void testSafeAddToMap_NullMap() {
        Map<String, Object> map = null;
        String key = "testKey";
        String value = "testValue";

        assertThrows(NullPointerException.class, () -> {
            MapUtils.safeAddToMap(map, key, value);
        });
    }

    @Test
    public void testSafeAddToMap_NullValue() {
        Map<String, Object> map = new HashMap<>();
        String key = "testKey";
        Object value = null;

        MapUtils.safeAddToMap(map, key, value);

        assertEquals("", map.get(key));
    }
}
