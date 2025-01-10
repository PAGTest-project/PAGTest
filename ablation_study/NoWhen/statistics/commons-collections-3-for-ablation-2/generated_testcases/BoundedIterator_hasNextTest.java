
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoundedIterator_hasNextTest {

    @Test
    void testHasNext_WithinBounds() {
        Iterator<String> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true);

        BoundedIterator<String> boundedIterator = new BoundedIterator<>(mockIterator, 0, 1);
        assertTrue(boundedIterator.hasNext());
    }

    @Test
    void testHasNext_OutOfBounds() {
        Iterator<String> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true);

        BoundedIterator<String> boundedIterator = new BoundedIterator<>(mockIterator, 0, 0);
        assertFalse(boundedIterator.hasNext());
    }

    @Test
    void testHasNext_IteratorExhausted() {
        Iterator<String> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(false);

        BoundedIterator<String> boundedIterator = new BoundedIterator<>(mockIterator, 0, 1);
        assertFalse(boundedIterator.hasNext());
    }
}
