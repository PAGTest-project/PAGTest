
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_collatedIteratorTest {

    @Test
    public void testCollatedIteratorWithNullComparator() {
        // Given
        Comparator<Integer> naturalComparator = Comparator.naturalOrder();
        List<Iterator<Integer>> iterators = Arrays.asList(
                Arrays.asList(1, 3, 5).iterator(),
                Arrays.asList(2, 4, 6).iterator()
        );

        // When
        Iterator<Integer> collatedIterator = IteratorUtils.collatedIterator(null, iterators);

        // Then
        assertTrue(collatedIterator instanceof CollatingIterator);
        assertEquals(1, collatedIterator.next());
        assertEquals(2, collatedIterator.next());
        assertEquals(3, collatedIterator.next());
        assertEquals(4, collatedIterator.next());
        assertEquals(5, collatedIterator.next());
        assertEquals(6, collatedIterator.next());
    }

    @Test
    public void testCollatedIteratorWithCustomComparator() {
        // Given
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        List<Iterator<Integer>> iterators = Arrays.asList(
                Arrays.asList(1, 3, 5).iterator(),
                Arrays.asList(2, 4, 6).iterator()
        );

        // When
        Iterator<Integer> collatedIterator = IteratorUtils.collatedIterator(reverseComparator, iterators);

        // Then
        assertTrue(collatedIterator instanceof CollatingIterator);
        assertEquals(6, collatedIterator.next());
        assertEquals(5, collatedIterator.next());
        assertEquals(4, collatedIterator.next());
        assertEquals(3, collatedIterator.next());
        assertEquals(2, collatedIterator.next());
        assertEquals(1, collatedIterator.next());
    }
}
