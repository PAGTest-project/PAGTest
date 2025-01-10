
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class SplitMapUtils_readableMapTest {

    @Test
    void testReadableMapWithIterableMap() {
        IterableMap<String, String> map = new HashMap<>();
        IterableMap<String, String> result = SplitMapUtils.readableMap(map);
        assertSame(map, result);
    }

    @Test
    void testReadableMapWithNonIterableMap() {
        Map<String, String> map = new HashMap<>();
        IterableMap<String, String> result = SplitMapUtils.readableMap(map);
        assertTrue(result instanceof IterableMap);
    }

    @Test
    void testReadableMapWithNonMapGet() {
        Get<String, String> get = new Get<String, String>() {
            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Set<Entry<String, String>> entrySet() {
                return null;
            }
        };
        IterableMap<String, String> result = SplitMapUtils.readableMap(get);
        assertTrue(result instanceof WrappedGet);
    }

    @Test
    void testReadableMapWithNullGet() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.readableMap(null);
        });
    }
}
