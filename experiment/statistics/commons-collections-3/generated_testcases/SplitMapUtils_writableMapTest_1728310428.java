
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SplitMapUtils_writableMapTest {

    @Test
    void testWritableMapWithMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        Map<String, String> result = SplitMapUtils.writableMap(map);
        assertEquals(map, result);
    }

    @Test
    void testWritableMapWithNonMap() {
        Put<String, String> put = new Put<String, String>() {
            @Override
            public Object put(Object key, Object value) {
                return null;
            }

            @Override
            public void putAll(Map map) {
            }

            @Override
            public void clear() {
            }
        };
        Map<String, String> result = SplitMapUtils.writableMap(put);
        assertTrue(result instanceof WrappedPut);
    }

    @Test
    void testWritableMapWithNull() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.writableMap(null);
        });
    }
}
