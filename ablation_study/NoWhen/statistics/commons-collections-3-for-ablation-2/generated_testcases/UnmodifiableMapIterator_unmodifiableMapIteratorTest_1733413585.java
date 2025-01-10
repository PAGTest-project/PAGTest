
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
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn("value");
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("next");

        // When
        MapIterator<String, String> result = UnmodifiableMapIterator.unmodifiableMapIterator(mockIterator);

        // Then
        assertSame(mockIterator, result);
        assertEquals("key", result.getKey());
        assertEquals("value", result.getValue());
        assertTrue(result.hasNext());
        assertEquals("next", result.next());
    }

    @Test
    void testUnmodifiableMapIteratorWithModifiableIterator() {
        // Given
        MapIterator<String, String> mockIterator = mock(MapIterator.class);
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn("value");
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("next");

        // When
        MapIterator<String, String> result = UnmodifiableMapIterator.unmodifiableMapIterator(mockIterator);

        // Then
        assertNotSame(mockIterator, result);
        assertEquals("key", result.getKey());
        assertEquals("value", result.getValue());
        assertTrue(result.hasNext());
        assertEquals("next", result.next());
    }

    @Test
    void testUnmodifiableMapIteratorWithNullIterator() {
        // Given
        MapIterator<String, String> nullIterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableMapIterator.unmodifiableMapIterator(nullIterator);
        });
    }
}
