
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_nextTest {

    private Comparator<Integer> comparator;
    private ArrayList<Integer> evens;
    private ArrayList<Integer> odds;

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
    public void testNextWithElements() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, evens.iterator(), odds.iterator());
        for (int i = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
            assertEquals(Integer.valueOf(i), iter.next());
            assertEquals(i % 2, iter.getIteratorIndex());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithoutElements() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        assertThrows(NoSuchElementException.class, iter::next);
    }

    @Test
    public void testNextWithEmptyIterators() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, new ArrayList<Integer>().iterator(), new ArrayList<Integer>().iterator());
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, iter::next);
    }

    @Test
    public void testNextWithOneEmptyIterator() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, evens.iterator(), new ArrayList<Integer>().iterator());
        for (int i = 0; i < 10; i++) {
            assertTrue(iter.hasNext());
            assertEquals(Integer.valueOf(i * 2), iter.next());
            assertEquals(0, iter.getIteratorIndex());
        }
        assertFalse(iter.hasNext());
    }
}
