
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableSortedBidiMap_mapIteratorTest {

    @Test
    void testMapIterator() {
        // Given
        SortedBidiMap<String, String> mockMap = mock(SortedBidiMap.class);
        OrderedMapIterator<String, String> mockIterator = mock(OrderedMapIterator.class);
        when(mockMap.mapIterator()).thenReturn(mockIterator);

        UnmodifiableSortedBidiMap<String, String> unmodifiableMap = new UnmodifiableSortedBidiMap<>(mockMap);

        // When
        OrderedMapIterator<String, String> result = unmodifiableMap.mapIterator();

        // Then
        assertTrue(result instanceof UnmodifiableOrderedMapIterator);
        assertSame(mockIterator, ((UnmodifiableOrderedMapIterator<String, String>) result).getIterator());
    }
}
