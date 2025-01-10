
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
        MapIterator<String, String> mockIterator = mock(MapIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("key");
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn("value");

        MapIterator<String, String> unmodifiableIterator = new UnmodifiableMapIterator<>(mockIterator);

        // When
        MapIterator<String, String> result = UnmodifiableMapIterator.unmodifiableMapIterator(unmodifiableIterator);

        // Then
        assertSame(unmodifiableIterator, result);
    }

    @Test
    void testUnmodifiableMapIteratorWithModifiableIterator() {
        // Given
        MapIterator<String, String> mockIterator = mock(MapIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("key");
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn("value");

        // When
        MapIterator<String, String> result = UnmodifiableMapIterator.unmodifiableMapIterator(mockIterator);

        // Then
        assertTrue(result instanceof UnmodifiableMapIterator);
        assertNotSame(mockIterator, result);
    }

    @Test
    void testUnmodifiableMapIteratorWithNullIterator() {
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableMapIterator.unmodifiableMapIterator(null);
        });
    }
}
