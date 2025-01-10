
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PeekingIterator_peekingIteratorTest {

    @Test
    void testPeekingIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            PeekingIterator.peekingIterator(null);
        });
    }

    @Test
    void testPeekingIteratorWithNonPeekingIterator() {
        Iterator<String> mockIterator = mock(Iterator.class);
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(mockIterator);
        assertNotNull(peekingIterator);
        assertNotSame(mockIterator, peekingIterator);
    }

    @Test
    void testPeekingIteratorWithPeekingIterator() {
        PeekingIterator<String> mockPeekingIterator = mock(PeekingIterator.class);
        PeekingIterator<String> result = PeekingIterator.peekingIterator(mockPeekingIterator);
        assertSame(mockPeekingIterator, result);
    }
}
