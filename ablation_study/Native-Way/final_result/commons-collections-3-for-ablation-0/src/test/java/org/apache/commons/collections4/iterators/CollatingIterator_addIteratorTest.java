
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_addIteratorTest {

    private CollatingIterator<Integer> collatingIterator;
    private Comparator<Integer> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new ComparableComparator<>();
        collatingIterator = new CollatingIterator<>(comparator);
    }

    @Test
    public void testAddIteratorSuccess() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        collatingIterator.addIterator(iterator);
        assertEquals(1, collatingIterator.getIterators().size());
    }

    @Test
    public void testAddIteratorNull() {
        assertThrows(NullPointerException.class, () -> {
            collatingIterator.addIterator(null);
        });
    }

    @Test
    public void testAddIteratorAfterIterationStarted() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        collatingIterator.addIterator(iterator);
        collatingIterator.hasNext(); // Start iteration
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.addIterator(iterator);
        });
    }

    @Test
    public void testAddIteratorMultiple() {
        Iterator<Integer> iterator1 = Arrays.asList(1, 3, 5).iterator();
        Iterator<Integer> iterator2 = Arrays.asList(2, 4, 6).iterator();
        collatingIterator.addIterator(iterator1);
        collatingIterator.addIterator(iterator2);
        assertEquals(2, collatingIterator.getIterators().size());
    }

    @Test
    public void testAddIteratorAndCheckNext() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        collatingIterator.addIterator(iterator);
        assertTrue(collatingIterator.hasNext());
        assertEquals(1, collatingIterator.next());
    }

    @Test
    public void testAddIteratorAndCheckHasNext() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        collatingIterator.addIterator(iterator);
        assertTrue(collatingIterator.hasNext());
        collatingIterator.next();
        assertTrue(collatingIterator.hasNext());
        collatingIterator.next();
        assertTrue(collatingIterator.hasNext());
        collatingIterator.next();
        assertFalse(collatingIterator.hasNext());
    }

    @Test
    public void testAddIteratorAndCheckGetIterators() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        collatingIterator.addIterator(iterator);
        List<Iterator<? extends Integer>> iterators = collatingIterator.getIterators();
        assertEquals(1, iterators.size());
        assertSame(iterator, iterators.get(0));
    }
}
