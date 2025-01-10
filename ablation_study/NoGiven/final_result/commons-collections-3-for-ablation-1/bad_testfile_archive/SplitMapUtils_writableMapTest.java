
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SplitMapUtils_writableMapTest {

    @Test
    public void testWritableMapWithMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);

        Map<String, Integer> result = SplitMapUtils.writableMap((Put<String, Integer>) map);

        assertEquals(map, result);
        assertTrue(result.containsKey("key1"));
        assertTrue(result.containsValue(2));
    }

    @Test
    public void testWritableMapWithNonMap() {
        Put<String, Integer> put = new Put<String, Integer>() {
            @Override
            public Object put(String key, Integer value) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends Integer> map) {
            }

            @Override
            public void clear() {
            }
        };

        Map<String, Integer> result = SplitMapUtils.writableMap(put);

        assertTrue(result instanceof Map);
    }

    @Test
    public void testWritableMapWithNull() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.writableMap(null);
        });
    }
}
