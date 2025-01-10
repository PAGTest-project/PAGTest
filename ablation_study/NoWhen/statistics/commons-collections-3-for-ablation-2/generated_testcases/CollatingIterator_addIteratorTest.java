
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_addIteratorTest {

    private Comparator<Integer> comparator;
    private ArrayList<Integer> evens;
    private ArrayList<Integer> odds;

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
    public void testAddIteratorValid() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator);
        Iterator<Integer> evenIterator = evens.iterator();
        collatingIterator.addIterator(evenIterator);
        assertEquals(1, collatingIterator.getIterators().size());
        assertSame(evenIterator, collatingIterator.getIterators().get(0));
    }

    @Test
    public void testAddIteratorNull() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator);
        assertThrows(NullPointerException.class, () -> {
            collatingIterator.addIterator(null);
        });
    }

    @Test
    public void testAddIteratorAfterIterationStarted() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator);
        collatingIterator.addIterator(evens.iterator());
        collatingIterator.hasNext(); // Start iteration
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.addIterator(odds.iterator());
        });
    }
}
