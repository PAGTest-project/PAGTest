
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableMapIterator_unmodifiableMapIteratorTest {

    @Test
    void testUnmodifiableMapIteratorWithUnmodifiableIterator() {
        // Given
        MapIterator<String, Integer> mockIterator = mock(MapIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("key");
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn(1);

        // When
        MapIterator<String, Integer> result = UnmodifiableMapIterator.unmodifiableMapIterator(mockIterator);

        // Then
        assertTrue(result instanceof Unmodifiable);
        assertEquals("key", result.getKey());
        assertEquals(1, result.getValue());
    }

    @Test
    void testUnmodifiableMapIteratorWithModifiableIterator() {
        // Given
        MapIterator<String, Integer> mockIterator = mock(MapIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("key");
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn(1);

        // When
        MapIterator<String, Integer> result = UnmodifiableMapIterator.unmodifiableMapIterator(mockIterator);

        // Then
        assertTrue(result instanceof UnmodifiableMapIterator);
        assertEquals("key", result.getKey());
        assertEquals(1, result.getValue());
    }

    @Test
    void testUnmodifiableMapIteratorWithNullIterator() {
        // Given
        MapIterator<String, Integer> nullIterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableMapIterator.unmodifiableMapIterator(nullIterator);
        });
    }
}
