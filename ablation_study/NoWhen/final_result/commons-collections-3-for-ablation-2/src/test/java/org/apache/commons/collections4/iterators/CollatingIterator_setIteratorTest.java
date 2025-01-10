
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_setIteratorTest {

    private Comparator<Integer> comparator;
    private List<Integer> evens;
    private List<Integer> odds;

    @BeforeEach
    public void setUp() throws Exception {
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
    }

    @Test
    public void testSetIteratorValid() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator);
        collatingIterator.addIterator(odds.iterator());
        collatingIterator.addIterator(evens.iterator());

        Iterator<Integer> newIterator = Arrays.asList(20, 22, 24).iterator();
        collatingIterator.setIterator(1, newIterator);

        List<Iterator<? extends Integer>> iterators = collatingIterator.getIterators();
        assertSame(newIterator, iterators.get(1));
    }

    @Test
    public void testSetIteratorNull() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator);
        collatingIterator.addIterator(odds.iterator());
        collatingIterator.addIterator(evens.iterator());

        assertThrows(NullPointerException.class, () -> {
            collatingIterator.setIterator(1, null);
        });
    }

    @Test
    public void testSetIteratorIndexOutOfBounds() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator);
        collatingIterator.addIterator(odds.iterator());
        collatingIterator.addIterator(evens.iterator());

        Iterator<Integer> newIterator = Arrays.asList(20, 22, 24).iterator();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            collatingIterator.setIterator(2, newIterator);
        });
    }

    @Test
    public void testSetIteratorAfterStart() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator);
        collatingIterator.addIterator(odds.iterator());
        collatingIterator.addIterator(evens.iterator());

        collatingIterator.hasNext(); // Start iteration

        Iterator<Integer> newIterator = Arrays.asList(20, 22, 24).iterator();
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.setIterator(1, newIterator);
        });
    }
}
