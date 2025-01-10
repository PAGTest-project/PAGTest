
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
    public void testCollatedIteratorWithComparator() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Iterator<Integer> iterator1 = Arrays.asList(1, 3, 5).iterator();
        Iterator<Integer> iterator2 = Arrays.asList(2, 4, 6).iterator();
        Collection<Iterator<? extends Integer>> iterators = Arrays.asList(iterator1, iterator2);

        Iterator<Integer> collatedIterator = IteratorUtils.collatedIterator(comparator, iterators);
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertTrue(result.equals(IteratorUtils.toList(collatedIterator)));
    }

    @Test
    public void testCollatedIteratorWithoutComparator() {
        Iterator<Integer> iterator1 = Arrays.asList(1, 3, 5).iterator();
        Iterator<Integer> iterator2 = Arrays.asList(2, 4, 6).iterator();
        Collection<Iterator<? extends Integer>> iterators = Arrays.asList(iterator1, iterator2);

        Iterator<Integer> collatedIterator = IteratorUtils.collatedIterator(null, iterators);
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertTrue(result.equals(IteratorUtils.toList(collatedIterator)));
    }
}
