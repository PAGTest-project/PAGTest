
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Map;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;

public class UnmodifiableMap_mapIteratorTest {

    @Test
    public void testMapIteratorWithIterableMap() {
        // Given
        IterableMap<String, String> iterableMap = mock(IterableMap.class);
        MapIterator<String, String> mockIterator = mock(MapIterator.class);
        when(iterableMap.mapIterator()).thenReturn(mockIterator);

        UnmodifiableMap<String, String> unmodifiableMap = UnmodifiableMap.unmodifiableMap(iterableMap);

        // When
        MapIterator<String, String> result = unmodifiableMap.mapIterator();

        // Then
        assertTrue(result instanceof UnmodifiableMapIterator);
        assertSame(mockIterator, ((UnmodifiableMapIterator<String, String>) result).getIterator());
    }

    @Test
    public void testMapIteratorWithoutIterableMap() {
        // Given
        Map<String, String> regularMap = mock(Map.class);
        UnmodifiableMap<String, String> unmodifiableMap = UnmodifiableMap.unmodifiableMap(regularMap);

        // When
        MapIterator<String, String> result = unmodifiableMap.mapIterator();

        // Then
        assertTrue(result instanceof UnmodifiableMapIterator);
        assertTrue(((UnmodifiableMapIterator<String, String>) result).getIterator() instanceof EntrySetMapIterator);
    }
}
