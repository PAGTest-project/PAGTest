
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.OrderedBidiMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableOrderedBidiMap_inverseOrderedBidiMapTest {

    @Test
    void testInverseOrderedBidiMap() {
        // Given
        OrderedBidiMap<String, Integer> originalMap = new DualHashBidiMap<>();
        originalMap.put("one", 1);
        originalMap.put("two", 2);
        UnmodifiableOrderedBidiMap<String, Integer> unmodifiableMap = UnmodifiableOrderedBidiMap.unmodifiableOrderedBidiMap(originalMap);

        // When
        OrderedBidiMap<Integer, String> inverseMap = unmodifiableMap.inverseBidiMap();

        // Then
        assertNotNull(inverseMap);
        assertEquals("one", inverseMap.get(1));
        assertEquals("two", inverseMap.get(2));
        assertSame(unmodifiableMap, inverseMap.inverseBidiMap());
    }
}
