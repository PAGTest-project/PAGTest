
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
        when(mockIterator.next()).thenReturn("test");
        when(mockIterator.hasPrevious()).thenReturn(false);
        when(mockIterator.previous()).thenThrow(new NoSuchElementException());

        // When
        ListIterator<String> result = UnmodifiableListIterator.unmodifiableListIterator(mockIterator);

        // Then
        assertTrue(result.hasNext());
        assertEquals("test", result.next());
        assertFalse(result.hasPrevious());
        assertThrows(NoSuchElementException.class, result::previous);
        assertThrows(UnsupportedOperationException.class, () -> result.add("new"));
        assertThrows(UnsupportedOperationException.class, result::remove);
        assertThrows(UnsupportedOperationException.class, () -> result.set("new"));
    }

    @Test
    void testUnmodifiableListIteratorWithModifiableIterator() {
        // Given
        ListIterator<String> mockIterator = mock(ListIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("test");
        when(mockIterator.hasPrevious()).thenReturn(false);
        when(mockIterator.previous()).thenThrow(new NoSuchElementException());

        // When
        ListIterator<String> result = UnmodifiableListIterator.unmodifiableListIterator(mockIterator);

        // Then
        assertTrue(result.hasNext());
        assertEquals("test", result.next());
        assertFalse(result.hasPrevious());
        assertThrows(NoSuchElementException.class, result::previous);
        assertThrows(UnsupportedOperationException.class, () -> result.add("new"));
        assertThrows(UnsupportedOperationException.class, result::remove);
        assertThrows(UnsupportedOperationException.class, () -> result.set("new"));
    }

    @Test
    void testUnmodifiableListIteratorWithNullIterator() {
        // Given
        ListIterator<String> nullIterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> UnmodifiableListIterator.unmodifiableListIterator(nullIterator));
    }
}
