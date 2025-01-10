
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableOrderedMap_unmodifiableOrderedMapTest {

    @Test
    public void testUnmodifiableOrderedMapWithUnmodifiableMap() {
        // Given
        OrderedMap<String, String> originalMap = new UnmodifiableOrderedMap<>(new org.apache.commons.collections4.map.LinkedMap<>());

        // When
        OrderedMap<String, String> resultMap = UnmodifiableOrderedMap.unmodifiableOrderedMap(originalMap);

        // Then
        assertSame(originalMap, resultMap);
        assertTrue(resultMap instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableOrderedMapWithModifiableMap() {
        // Given
        OrderedMap<String, String> originalMap = new org.apache.commons.collections4.map.LinkedMap<>();

        // When
        OrderedMap<String, String> resultMap = UnmodifiableOrderedMap.unmodifiableOrderedMap(originalMap);

        // Then
        assertTrue(resultMap instanceof UnmodifiableOrderedMap);
        assertTrue(resultMap instanceof Unmodifiable);
    }
}
