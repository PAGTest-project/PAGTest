
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class SplitMapUtils_readableMapTest {

    @Test
    void testReadableMapWithIterableMap() {
        IterableMap<String, String> iterableMap = new HashMap<>();
        IterableMap<String, String> result = SplitMapUtils.readableMap(iterableMap);
        assertSame(iterableMap, result);
    }

    @Test
    void testReadableMapWithNonIterableMap() {
        Map<String, String> nonIterableMap = new HashMap<>();
        IterableMap<String, String> result = SplitMapUtils.readableMap(nonIterableMap);
        assertTrue(result instanceof IterableMap);
    }

    @Test
    void testReadableMapWithNonMapGet() {
        Get<String, String> nonMapGet = new Get<String, String>() {
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
        IterableMap<String, String> result = SplitMapUtils.readableMap(nonMapGet);
        assertTrue(result instanceof WrappedGet);
    }

    @Test
    void testReadableMapWithNullGet() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.readableMap(null);
        });
    }
}
