
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UnmodifiableOrderedBidiMap_mapIteratorTest {

    @Test
    public void testMapIterator() {
        // Given
        OrderedMapIterator<String, String> mockIterator = mock(OrderedMapIterator.class);
        UnmodifiableOrderedBidiMap<String, String> map = new UnmodifiableOrderedBidiMap<>(mock(OrderedMapIterator.class));
        when(map.decorated()).thenReturn(map);
        when(map.mapIterator()).thenReturn(mockIterator);

        // When
        OrderedMapIterator<String, String> result = map.mapIterator();

        // Then
        assertTrue(result instanceof UnmodifiableOrderedMapIterator);
    }
}
