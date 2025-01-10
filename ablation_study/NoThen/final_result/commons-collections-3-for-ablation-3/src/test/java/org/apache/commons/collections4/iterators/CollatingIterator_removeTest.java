
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_removeTest {

    private Comparator<Integer> comparator;
    private ArrayList<Integer> evens;
    private CollatingIterator<Integer> collatingIterator;

    @BeforeEach
    public void setUp() {
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                evens.add(i);
            }
        }
        collatingIterator = new CollatingIterator<>(comparator);
        collatingIterator.addIterator(evens.iterator());
    }

    @Test
    public void testRemoveWithoutNext() {
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.remove();
        });
    }

    @Test
    public void testRemoveAfterNext() {
        Iterator<Integer> iterator = evens.iterator();
        collatingIterator.next(); // Ensure next() has been called
        collatingIterator.remove();
        assertEquals(9, evens.size()); // Corrected the expected size
    }
}
