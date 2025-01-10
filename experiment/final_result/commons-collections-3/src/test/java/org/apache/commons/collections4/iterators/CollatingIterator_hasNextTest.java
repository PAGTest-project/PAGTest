
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_hasNextTest {

    private Comparator<Integer> comparator;
    private List<Integer> evens;
    private List<Integer> odds;

    @BeforeEach
    public void setUp() {
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
    }

    @Test
    public void testHasNextWithElements() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, evens.iterator(), odds.iterator());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextWithoutElements() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, new ArrayList<Integer>().iterator(), new ArrayList<Integer>().iterator());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithOneEmptyIterator() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, evens.iterator(), new ArrayList<Integer>().iterator());
        assertTrue(iter.hasNext());
    }
}
