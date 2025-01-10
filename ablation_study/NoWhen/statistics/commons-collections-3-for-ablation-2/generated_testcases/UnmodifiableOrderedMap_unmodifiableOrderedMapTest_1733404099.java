
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableOrderedMap_unmodifiableOrderedMapTest {

    @Test
    void testUnmodifiableOrderedMapWithUnmodifiableMap() {
        // Given
        OrderedMap<String, String> unmodifiableMap = new UnmodifiableOrderedMap<>(new org.apache.commons.collections4.map.LinkedMap<>());

        // When
        OrderedMap<String, String> result = UnmodifiableOrderedMap.unmodifiableOrderedMap(unmodifiableMap);

        // Then
        assertSame(unmodifiableMap, result);
    }

    @Test
    void testUnmodifiableOrderedMapWithModifiableMap() {
        // Given
        OrderedMap<String, String> modifiableMap = new org.apache.commons.collections4.map.LinkedMap<>();

        // When
        OrderedMap<String, String> result = UnmodifiableOrderedMap.unmodifiableOrderedMap(modifiableMap);

        // Then
        assertTrue(result instanceof UnmodifiableOrderedMap);
        assertNotSame(modifiableMap, result);
    }
}
