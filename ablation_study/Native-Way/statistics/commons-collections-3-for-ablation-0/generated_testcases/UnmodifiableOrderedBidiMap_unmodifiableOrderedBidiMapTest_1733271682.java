
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableOrderedBidiMap_unmodifiableOrderedBidiMapTest {

    private OrderedBidiMap<String, String> modifiableMap;
    private OrderedBidiMap<String, String> unmodifiableMap;

    @BeforeEach
    public void setUp() {
        modifiableMap = new DualHashBidiMap<>();
        modifiableMap.put("key1", "value1");
        modifiableMap.put("key2", "value2");

        unmodifiableMap = UnmodifiableOrderedBidiMap.unmodifiableOrderedBidiMap(modifiableMap);
    }

    @Test
    public void testUnmodifiableOrderedBidiMapWithModifiableMap() {
        assertNotSame(modifiableMap, unmodifiableMap);
        assertTrue(unmodifiableMap instanceof Unmodifiable);
        assertEquals(modifiableMap.size(), unmodifiableMap.size());
        assertEquals("value1", unmodifiableMap.get("key1"));
    }

    @Test
    public void testUnmodifiableOrderedBidiMapWithUnmodifiableMap() {
        OrderedBidiMap<String, String> alreadyUnmodifiableMap = UnmodifiableOrderedBidiMap.unmodifiableOrderedBidiMap(unmodifiableMap);
        assertSame(unmodifiableMap, alreadyUnmodifiableMap);
    }

    @Test
    public void testUnmodifiableOrderedBidiMapModification() {
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableMap.put("key3", "value3"));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableMap.remove("key1"));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableMap.clear());
    }
}
