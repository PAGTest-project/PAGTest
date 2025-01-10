
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkippingIterator_removeTest {

    @Test
    void testRemoveBeforeNext() {
        Iterator<String> mockIterator = mock(Iterator.class);
        SkippingIterator<String> skippingIterator = new SkippingIterator<>(mockIterator, 5);

        // Given: pos is less than or equal to offset
        // When: remove() is called
        // Then: IllegalStateException is thrown
        assertThrows(IllegalStateException.class, skippingIterator::remove);
    }

    @Test
    void testRemoveAfterNext() {
        Iterator<String> mockIterator = mock(Iterator.class);
        when(mockIterator.hasNext()).thenReturn(true, false);
        when(mockIterator.next()).thenReturn("element");

        SkippingIterator<String> skippingIterator = new SkippingIterator<>(mockIterator, 0);

        // Given: next() has been called, pos is greater than offset
        skippingIterator.next();

        // When: remove() is called
        // Then: super.remove() is called
        skippingIterator.remove();
        verify(mockIterator).remove();
    }
}
