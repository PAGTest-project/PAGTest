
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollatingIterator_setIteratorTest {

    @Test
    void testSetIterator_Success() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>();
        Iterator<Integer> mockIterator = mock(Iterator.class);
        collatingIterator.addIterator(mock(Iterator.class));
        collatingIterator.setIterator(0, mockIterator);
        assertEquals(mockIterator, collatingIterator.getIterators().get(0));
    }

    @Test
    void testSetIterator_NullIterator() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>();
        collatingIterator.addIterator(mock(Iterator.class));
        assertThrows(NullPointerException.class, () -> collatingIterator.setIterator(0, null));
    }

    @Test
    void testSetIterator_IterationStarted() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>();
        collatingIterator.addIterator(mock(Iterator.class));
        collatingIterator.hasNext(); // Start iteration
        Iterator<Integer> mockIterator = mock(Iterator.class);
        assertThrows(IllegalStateException.class, () -> collatingIterator.setIterator(0, mockIterator));
    }
}
