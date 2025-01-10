
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_removeTest {

    private CollatingIterator<Integer> collatingIterator;
    private List<Integer> l1;
    private List<Integer> l2;

    @BeforeEach
    public void setUp() {
        l1 = new ArrayList<>(Arrays.asList(1, 3, 5));
        l2 = new ArrayList<>(Arrays.asList(2, 4, 6));
        collatingIterator = new CollatingIterator<>(new ComparableComparator<>(), l1.iterator(), l2.iterator());
    }

    @Test
    public void testRemoveWithoutNext() {
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.remove();
        });
    }

    @Test
    public void testRemoveAfterNext() {
        collatingIterator.next(); // Move to the first element
        collatingIterator.remove(); // Remove the last returned element
        assertFalse(l1.contains(1)); // Ensure the element was removed from the source list
    }

    @Test
    public void testRemoveTwice() {
        collatingIterator.next(); // Move to the first element
        collatingIterator.remove(); // Remove the last returned element
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.remove(); // Attempt to remove again without calling next
        });
    }

    @Test
    public void testRemoveAfterEndOfIteration() {
        while (collatingIterator.hasNext()) {
            collatingIterator.next();
        }
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.remove(); // Attempt to remove after the end of iteration
        });
    }
}
