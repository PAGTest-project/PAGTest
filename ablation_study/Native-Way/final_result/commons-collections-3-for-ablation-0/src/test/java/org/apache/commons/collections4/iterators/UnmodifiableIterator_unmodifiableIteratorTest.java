
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableIterator_unmodifiableIteratorTest {

    @Test
    void testUnmodifiableIteratorWithModifiableIterator() {
        Iterator<String> mockIterator = mock(Iterator.class);
        Iterator<String> result = UnmodifiableIterator.unmodifiableIterator(mockIterator);
        assertNotSame(mockIterator, result);
        assertTrue(result instanceof UnmodifiableIterator);
    }

    @Test
    void testUnmodifiableIteratorWithUnmodifiableIterator() {
        Iterator<String> mockIterator = mock(Iterator.class);
        Iterator<String> mockUnmodifiableIterator = UnmodifiableIterator.unmodifiableIterator(mockIterator);
        Iterator<String> result = UnmodifiableIterator.unmodifiableIterator(mockUnmodifiableIterator);
        assertSame(mockUnmodifiableIterator, result);
    }

    @Test
    void testUnmodifiableIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableIterator.unmodifiableIterator(null);
        });
    }
}
