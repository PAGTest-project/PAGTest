
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableIterator_unmodifiableIteratorTest {

    @Test
    void testUnmodifiableIteratorWithModifiableIterator() {
        // Given
        Iterator<String> modifiableIterator = mock(Iterator.class);

        // When
        Iterator<String> result = UnmodifiableIterator.unmodifiableIterator(modifiableIterator);

        // Then
        assertNotSame(modifiableIterator, result);
        assertTrue(result instanceof UnmodifiableIterator);
    }

    @Test
    void testUnmodifiableIteratorWithUnmodifiableIterator() {
        // Given
        Iterator<String> modifiableIterator = mock(Iterator.class);
        Iterator<String> unmodifiableIterator = UnmodifiableIterator.unmodifiableIterator(modifiableIterator);

        // When
        Iterator<String> result = UnmodifiableIterator.unmodifiableIterator(unmodifiableIterator);

        // Then
        assertSame(unmodifiableIterator, result);
    }

    @Test
    void testUnmodifiableIteratorWithNullIterator() {
        // Given
        Iterator<String> nullIterator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableIterator.unmodifiableIterator(nullIterator);
        });
    }
}
