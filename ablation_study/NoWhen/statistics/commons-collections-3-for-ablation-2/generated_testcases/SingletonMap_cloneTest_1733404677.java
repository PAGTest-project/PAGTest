
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SingletonMap_cloneTest {

    @Test
    public void testClone_SuccessfulClone() {
        // Given
        SingletonMap<String, String> originalMap = new SingletonMap<>("key", "value");

        // When
        SingletonMap<String, String> clonedMap = originalMap.clone();

        // Then
        assertEquals(originalMap, clonedMap);
        assertEquals(originalMap.hashCode(), clonedMap.hashCode());
    }

    @Test
    public void testClone_CloneNotSupportedException() {
        // Given
        SingletonMap<String, String> originalMap = new SingletonMap<>("key", "value");

        // When and Then
        assertThrows(UnsupportedOperationException.class, () -> {
            originalMap.clone();
        });
    }
}
