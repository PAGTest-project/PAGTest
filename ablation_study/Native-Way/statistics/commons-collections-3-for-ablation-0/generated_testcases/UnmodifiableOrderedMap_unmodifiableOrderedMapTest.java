
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableOrderedMap_unmodifiableOrderedMapTest {

    private OrderedMap<String, String> modifiableMap;
    private OrderedMap<String, String> unmodifiableMap;

    @BeforeEach
    public void setUp() {
        modifiableMap = new ListOrderedMap<>();
        modifiableMap.put("key1", "value1");
        modifiableMap.put("key2", "value2");

        unmodifiableMap = UnmodifiableOrderedMap.unmodifiableOrderedMap(modifiableMap);
    }

    @Test
    public void testUnmodifiableOrderedMapWithModifiableMap() {
        OrderedMap<String, String> result = UnmodifiableOrderedMap.unmodifiableOrderedMap(modifiableMap);
        assertNotNull(result);
        assertTrue(result instanceof UnmodifiableOrderedMap);
        assertEquals(modifiableMap.size(), result.size());
    }

    @Test
    public void testUnmodifiableOrderedMapWithUnmodifiableMap() {
        OrderedMap<String, String> result = UnmodifiableOrderedMap.unmodifiableOrderedMap(unmodifiableMap);
        assertNotNull(result);
        assertSame(unmodifiableMap, result);
    }

    @Test
    public void testUnmodifiableOrderedMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableOrderedMap.unmodifiableOrderedMap(null);
        });
    }
}
