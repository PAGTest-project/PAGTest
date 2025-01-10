
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableListIterator_unmodifiableListIteratorTest {

    @Test
    void testUnmodifiableListIteratorWithUnmodifiableIterator() {
        // Given
        ListIterator<String> mockIterator = mock(ListIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("element");
        when(mockIterator.hasPrevious()).thenReturn(false);
        when(mockIterator.previous()).thenThrow(new NoSuchElementException());

        // When
        ListIterator<String> result = UnmodifiableListIterator.unmodifiableListIterator(mockIterator);

        // Then
        assertSame(mockIterator, result);
    }

    @Test
    void testUnmodifiableListIteratorWithModifiableIterator() {
        // Given
        ListIterator<String> mockIterator = mock(ListIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("element");
        when(mockIterator.hasPrevious()).thenReturn(false);
        when(mockIterator.previous()).thenThrow(new NoSuchElementException());

        // When
        ListIterator<String> result = UnmodifiableListIterator.unmodifiableListIterator(mockIterator);

        // Then
        assertNotSame(mockIterator, result);
        assertTrue(result instanceof Unmodifiable);
    }

    @Test
    void testUnmodifiableListIteratorWithNullIterator() {
        // Given
        ListIterator<String> nullIterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableListIterator.unmodifiableListIterator(nullIterator);
        });
    }
}
