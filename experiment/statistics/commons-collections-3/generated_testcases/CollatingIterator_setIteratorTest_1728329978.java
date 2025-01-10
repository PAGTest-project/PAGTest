
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollatingIterator_setIteratorTest {

    @Test
    void testSetIterator_Success() {
        // Given
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(null, 2);
        Iterator<Integer> mockIterator = mock(Iterator.class);

        // When
        collatingIterator.setIterator(0, mockIterator);

        // Then
        assertEquals(mockIterator, collatingIterator.getIterators().get(0));
    }

    @Test
    void testSetIterator_NullIterator() {
        // Given
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(null, 2);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            collatingIterator.setIterator(0, null);
        });
    }

    @Test
    void testSetIterator_IterationStarted() {
        // Given
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(null, 2);
        Iterator<Integer> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true);
        collatingIterator.addIterator(mockIterator);
        collatingIterator.next(); // Start iteration

        // When & Then
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.setIterator(0, mockIterator);
        });
    }
}
