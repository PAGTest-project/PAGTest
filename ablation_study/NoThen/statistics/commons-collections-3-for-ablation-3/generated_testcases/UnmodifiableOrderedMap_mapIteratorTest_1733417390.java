
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.map.UnmodifiableOrderedMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UnmodifiableOrderedMap_mapIteratorTest {

    @Test
    public void testMapIterator() {
        // Given
        OrderedMap<String, String> mockMap = mock(OrderedMap.class);
        OrderedMapIterator<String, String> mockIterator = mock(OrderedMapIterator.class);
        when(mockMap.mapIterator()).thenReturn(mockIterator);

        UnmodifiableOrderedMap<String, String> unmodifiableMap = new UnmodifiableOrderedMap<>(mockMap);

        // When
        OrderedMapIterator<String, String> resultIterator = unmodifiableMap.mapIterator();

        // Then
        assertNotNull(resultIterator);
        assertTrue(resultIterator instanceof UnmodifiableOrderedMapIterator);
    }
}
