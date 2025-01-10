
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.SortedBidiMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableSortedBidiMap_inverseBidiMapTest {

    @Test
    void testInverseBidiMap() {
        // Given
        SortedBidiMap<String, Integer> originalMap = new DualTreeBidiMap<>();
        originalMap.put("one", 1);
        UnmodifiableSortedBidiMap<String, Integer> unmodifiableMap = new UnmodifiableSortedBidiMap<>(originalMap);

        // When
        SortedBidiMap<Integer, String> inverseMap = unmodifiableMap.inverseBidiMap();

        // Then
        assertNotNull(inverseMap);
        assertEquals("one", inverseMap.get(1));
        assertSame(unmodifiableMap, inverseMap.inverseBidiMap());
    }
}
