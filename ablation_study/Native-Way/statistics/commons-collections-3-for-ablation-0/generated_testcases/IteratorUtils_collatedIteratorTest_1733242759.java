
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_collatedIteratorTest {

    @Test
    public void testCollatedIteratorWithNullComparator() {
        // Given
        Comparator<Integer> comparator = null;
        Collection<Iterator<? extends Integer>> iterators = Arrays.asList(
            Arrays.asList(1, 3, 5).iterator(),
            Arrays.asList(2, 4, 6).iterator()
        );

        // When
        Iterator<Integer> result = IteratorUtils.collatedIterator(comparator, iterators);

        // Then
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertTrue(expected.equals(IteratorUtils.toList(result)));
    }

    @Test
    public void testCollatedIteratorWithCustomComparator() {
        // Given
        Comparator<Integer> comparator = Comparator.reverseOrder();
        Collection<Iterator<? extends Integer>> iterators = Arrays.asList(
            Arrays.asList(1, 3, 5).iterator(),
            Arrays.asList(2, 4, 6).iterator()
        );

        // When
        Iterator<Integer> result = IteratorUtils.collatedIterator(comparator, iterators);

        // Then
        List<Integer> expected = Arrays.asList(6, 5, 4, 3, 2, 1);
        assertTrue(expected.equals(IteratorUtils.toList(result)));
    }
}
