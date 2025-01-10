
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
        when(mockIterator instanceof Unmodifiable).thenReturn(true);

        // When
        OrderedMapIterator<String, String> result = UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(mockIterator);

        // Then
        assertSame(mockIterator, result);
    }

    @Test
    void testUnmodifiableOrderedMapIteratorWithModifiableIterator() {
        // Given
        OrderedMapIterator<String, String> mockIterator = mock(OrderedMapIterator.class);
        when(mockIterator instanceof Unmodifiable).thenReturn(false);

        // When
        OrderedMapIterator<String, String> result = UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(mockIterator);

        // Then
        assertTrue(result instanceof UnmodifiableOrderedMapIterator);
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
