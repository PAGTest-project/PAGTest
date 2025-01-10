
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_hasNextTest {

    private Comparator<Integer> comparator;
    private List<Integer> list1;
    private List<Integer> list2;

    @BeforeEach
    public void setUp() {
        comparator = new ComparableComparator<>();
        list1 = Arrays.asList(1, 3, 5);
        list2 = Arrays.asList(2, 4, 6);
    }

    @Test
    public void testHasNextWithElements() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator, list1.iterator(), list2.iterator());
        assertTrue(collatingIterator.hasNext());
    }

    @Test
    public void testHasNextWithoutElements() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator, new ArrayList<Integer>().iterator(), new ArrayList<Integer>().iterator());
        assertFalse(collatingIterator.hasNext());
    }

    @Test
    public void testHasNextWithOneEmptyIterator() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator, list1.iterator(), new ArrayList<Integer>().iterator());
        assertTrue(collatingIterator.hasNext());
    }

    @Test
    public void testHasNextAfterIteration() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(comparator, list1.iterator(), list2.iterator());
        while (collatingIterator.hasNext()) {
            collatingIterator.next();
        }
        assertFalse(collatingIterator.hasNext());
    }

    @Test
    public void testHasNextWithNullComparator() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(null, list1.iterator(), list2.iterator());
        try {
            collatingIterator.hasNext();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().startsWith("You must invoke setComparator"));
        }
    }

    @Test
    public void testHasNextWithSetComparator() {
        CollatingIterator<Integer> collatingIterator = new CollatingIterator<>(null, list1.iterator(), list2.iterator());
        collatingIterator.setComparator(comparator);
        assertTrue(collatingIterator.hasNext());
    }
}
