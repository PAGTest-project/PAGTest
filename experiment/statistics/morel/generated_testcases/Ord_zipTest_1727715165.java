
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class Ord_zipTest {

    @Test
    void testZipWithNonEmptyIterator() {
        // Given
        Iterator<String> inputIterator = ImmutableList.of("a", "b", "c").iterator();

        // When
        Iterator<Ord<String>> resultIterator = Ord.zip(inputIterator);

        // Then
        assertTrue(resultIterator.hasNext());
        assertEquals(new Ord<>(0, "a"), resultIterator.next());
        assertTrue(resultIterator.hasNext());
        assertEquals(new Ord<>(1, "b"), resultIterator.next());
        assertTrue(resultIterator.hasNext());
        assertEquals(new Ord<>(2, "c"), resultIterator.next());
        assertFalse(resultIterator.hasNext());
        assertThrows(NoSuchElementException.class, resultIterator::next);
    }

    @Test
    void testZipWithEmptyIterator() {
        // Given
        Iterator<String> inputIterator = ImmutableList.of().iterator();

        // When
        Iterator<Ord<String>> resultIterator = Ord.zip(inputIterator);

        // Then
        assertFalse(resultIterator.hasNext());
        assertThrows(NoSuchElementException.class, resultIterator::next);
    }
}
