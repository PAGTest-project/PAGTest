
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableOrderedMap_unmodifiableOrderedMapTest {

    @Test
    void testUnmodifiableOrderedMapWithUnmodifiableMap() {
        // Given
        OrderedMap<String, String> mockMap = mock(OrderedMap.class);
        when(mockMap.isUnmodifiable()).thenReturn(true);

        // When
        OrderedMap<String, String> result = UnmodifiableOrderedMap.unmodifiableOrderedMap(mockMap);

        // Then
        assertSame(mockMap, result);
    }

    @Test
    void testUnmodifiableOrderedMapWithModifiableMap() {
        // Given
        OrderedMap<String, String> mockMap = mock(OrderedMap.class);
        when(mockMap.isUnmodifiable()).thenReturn(false);

        // When
        OrderedMap<String, String> result = UnmodifiableOrderedMap.unmodifiableOrderedMap(mockMap);

        // Then
        assertTrue(result instanceof UnmodifiableOrderedMap);
        assertNotSame(mockMap, result);
    }
}
