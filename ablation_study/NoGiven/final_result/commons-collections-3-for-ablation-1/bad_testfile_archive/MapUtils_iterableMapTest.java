
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MapUtils_iterableMapTest {

    @Test
    void testIterableMapWithNonNullMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);

        IterableMap<String, Integer> result = MapUtils.iterableMap(map);

        assertNotNull(result);
        assertEquals(map, result);
    }

    @Test
    void testIterableMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> {
            MapUtils.iterableMap(null);
        });
    }

    @Test
    void testIterableMapWithIterableMapInstance() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        IterableMap<String, Integer> iterableMap = new org.apache.commons.collections4.map.AbstractMapDecorator<>(map) {};

        IterableMap<String, Integer> result = MapUtils.iterableMap(iterableMap);

        assertSame(iterableMap, result);
    }
}
