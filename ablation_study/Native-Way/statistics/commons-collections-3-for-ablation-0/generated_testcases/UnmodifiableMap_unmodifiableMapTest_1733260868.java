
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableMap_unmodifiableMapTest {

    @Test
    public void testUnmodifiableMapWithUnmodifiableMap() {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

        Map<String, String> unmodifiableOriginalMap = new UnmodifiableMap<>(originalMap);
        Map<String, String> resultMap = UnmodifiableMap.unmodifiableMap(unmodifiableOriginalMap);

        assertSame(unmodifiableOriginalMap, resultMap);
    }

    @Test
    public void testUnmodifiableMapWithModifiableMap() {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

        Map<String, String> resultMap = UnmodifiableMap.unmodifiableMap(originalMap);

        assertTrue(resultMap instanceof UnmodifiableMap);
        assertNotSame(originalMap, resultMap);
    }

    @Test
    public void testUnmodifiableMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> UnmodifiableMap.unmodifiableMap(null));
    }
}
