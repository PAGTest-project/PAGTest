
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class Ord_zipTest {

    @Test
    void testZipIterator() {
        // Given
        Iterable<String> iterable = ImmutableList.of("a", "b", "c");
        Iterator<Ord<String>> ordIterator = Ord.zip(iterable.iterator());

        // When
        assertTrue(ordIterator.hasNext());
        assertEquals(new Ord<>(0, "a"), ordIterator.next());
        assertTrue(ordIterator.hasNext());
        assertEquals(new Ord<>(1, "b"), ordIterator.next());
        assertTrue(ordIterator.hasNext());
        assertEquals(new Ord<>(2, "c"), ordIterator.next());
        assertFalse(ordIterator.hasNext());

        // Then
        assertThrows(NoSuchElementException.class, ordIterator::next);
    }
}
