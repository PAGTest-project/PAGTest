
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableListIterator_unmodifiableListIteratorTest {

    @Test
    void testUnmodifiableListIteratorWithUnmodifiableIterator() {
        ListIterator<String> mockIterator = mock(ListIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("test");

        ListIterator<String> unmodifiableIterator = UnmodifiableListIterator.unmodifiableListIterator(mockIterator);

        assertTrue(unmodifiableIterator.hasNext());
        assertEquals("test", unmodifiableIterator.next());
    }

    @Test
    void testUnmodifiableListIteratorWithModifiableIterator() {
        ListIterator<String> mockIterator = mock(ListIterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        when(mockIterator.next()).thenReturn("test");

        ListIterator<String> unmodifiableIterator = UnmodifiableListIterator.unmodifiableListIterator(mockIterator);

        assertTrue(unmodifiableIterator.hasNext());
        assertEquals("test", unmodifiableIterator.next());
    }

    @Test
    void testUnmodifiableListIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableListIterator.unmodifiableListIterator(null);
        });
    }
}
