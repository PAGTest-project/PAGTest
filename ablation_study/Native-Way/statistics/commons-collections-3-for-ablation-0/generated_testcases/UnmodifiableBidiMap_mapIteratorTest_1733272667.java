
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.AbstractMapTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnmodifiableBidiMap_mapIteratorTest {

    @Test
    public void testMapIterator() {
        // Given
        BidiMap<String, String> mockBidiMap = mock(BidiMap.class);
        MapIterator<String, String> mockMapIterator = mock(MapIterator.class);
        when(mockBidiMap.mapIterator()).thenReturn(mockMapIterator);

        UnmodifiableBidiMap<String, String> unmodifiableBidiMap = new UnmodifiableBidiMap<>(mockBidiMap);

        // When
        MapIterator<String, String> result = unmodifiableBidiMap.mapIterator();

        // Then
        assertNotNull(result);
    }
}
