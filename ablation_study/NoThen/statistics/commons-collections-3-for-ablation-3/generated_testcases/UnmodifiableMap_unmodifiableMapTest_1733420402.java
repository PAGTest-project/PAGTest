
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_unmodifiableMapTest {

    @Test
    public void testUnmodifiableMapWithUnmodifiableMap() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        Map<String, String> unmodifiableMap = new UnmodifiableMap<>(originalMap);

        // When
        Map<String, String> result = UnmodifiableMap.unmodifiableMap(unmodifiableMap);

        // Then
        assertSame(unmodifiableMap, result);
        assertTrue(result instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableMapWithModifiableMap() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");

        // When
        Map<String, String> result = UnmodifiableMap.unmodifiableMap(originalMap);

        // Then
        assertTrue(result instanceof UnmodifiableMap);
        assertTrue(result instanceof Unmodifiable);
    }
}
