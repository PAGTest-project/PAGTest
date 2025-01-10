
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_getIteratorIndexTest {

    private Comparator<Integer> comparator;
    private ArrayList<Integer> evens;
    private CollatingIterator<Integer> collatingIterator;

    @BeforeEach
    public void setUp() throws Exception {
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            }
        }
        collatingIterator = new CollatingIterator<>(comparator);
        collatingIterator.addIterator(evens.iterator());
    }

    @Test
    public void testGetIteratorIndexSuccess() {
        collatingIterator.next(); // This should set lastReturned to 0
        assertEquals(0, collatingIterator.getIteratorIndex());
    }

    @Test
    public void testGetIteratorIndexNoValueReturnedYet() {
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.getIteratorIndex();
        });
    }
}
