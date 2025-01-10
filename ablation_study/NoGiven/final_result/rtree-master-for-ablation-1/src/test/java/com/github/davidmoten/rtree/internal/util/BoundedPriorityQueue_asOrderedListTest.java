
package com.github.davidmoten.rtree.internal.util;

import static com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BoundedPriorityQueue_asOrderedListTest {

    private static final Comparator<Integer> comparator = Comparator.naturalOrder();
    private BoundedPriorityQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = create(3, comparator);
    }

    @Test
    public void testAsOrderedListWithEmptyQueue() {
        List<Integer> orderedList = queue.asOrderedList();
        assertTrue(orderedList.isEmpty());
    }

    @Test
    public void testAsOrderedListWithSingleItem() {
        queue.add(5);
        List<Integer> orderedList = queue.asOrderedList();
        assertEquals(1, orderedList.size());
        assertEquals(Integer.valueOf(5), orderedList.get(0));
    }

    @Test
    public void testAsOrderedListWithMultipleItems() {
        queue.add(5);
        queue.add(3);
        queue.add(7);
        List<Integer> orderedList = queue.asOrderedList();
        assertEquals(3, orderedList.size());
        assertEquals(Integer.valueOf(3), orderedList.get(0));
        assertEquals(Integer.valueOf(5), orderedList.get(1));
        assertEquals(Integer.valueOf(7), orderedList.get(2));
    }

    @Test
    public void testAsOrderedListWithMaxSizeReached() {
        queue.add(5);
        queue.add(3);
        queue.add(7);
        queue.add(2); // This should replace the smallest element (3)
        List<Integer> orderedList = queue.asOrderedList();
        assertEquals(3, orderedList.size());
        assertEquals(Integer.valueOf(2), orderedList.get(0));
        assertEquals(Integer.valueOf(5), orderedList.get(1));
        assertEquals(Integer.valueOf(7), orderedList.get(2));
    }
}
