
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableOrderedMapIterator_unmodifiableOrderedMapIteratorTest {

    @Test
    void testUnmodifiableOrderedMapIteratorWithUnmodifiableIterator() {
        // Given
        OrderedMapIterator<String, String> mockIterator = mock(OrderedMapIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn("value");

        // When
        OrderedMapIterator<String, String> result = UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(mockIterator);

        // Then
        assertTrue(result instanceof Unmodifiable);
    }

    @Test
    void testUnmodifiableOrderedMapIteratorWithModifiableIterator() {
        // Given
        OrderedMapIterator<String, String> mockIterator = mock(OrderedMapIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.getKey()).thenReturn("key");
        when(mockIterator.getValue()).thenReturn("value");

        // When
        OrderedMapIterator<String, String> result = UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(mockIterator);

        // Then
        assertNotSame(mockIterator, result);
        assertTrue(result instanceof Unmodifiable);
    }

    @Test
    void testUnmodifiableOrderedMapIteratorWithNullIterator() {
        // Given
        OrderedMapIterator<String, String> nullIterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(nullIterator);
        });
    }
}
