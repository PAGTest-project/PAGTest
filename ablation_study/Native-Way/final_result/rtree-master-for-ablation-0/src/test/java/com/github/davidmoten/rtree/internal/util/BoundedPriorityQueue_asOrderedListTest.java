
package com.github.davidmoten.rtree.internal.util;

import static com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
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
        List<Integer> result = queue.asOrderedList();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAsOrderedListWithSingleElement() {
        queue.add(5);
        List<Integer> result = queue.asOrderedList();
        assertEquals(Arrays.asList(5), result);
    }

    @Test
    public void testAsOrderedListWithMultipleElements() {
        queue.add(3);
        queue.add(1);
        queue.add(2);
        List<Integer> result = queue.asOrderedList();
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testAsOrderedListWithMaxSizeElements() {
        queue.add(3);
        queue.add(1);
        queue.add(2);
        queue.add(4); // This should replace the smallest element
        List<Integer> result = queue.asOrderedList();
        assertEquals(Arrays.asList(2, 3, 4), result);
    }

    @Test
    public void testAsOrderedListWithMixedOrder() {
        queue.add(5);
        queue.add(2);
        queue.add(4);
        queue.add(1);
        queue.add(3);
        List<Integer> result = queue.asOrderedList();
        assertEquals(Arrays.asList(2, 3, 4), result);
    }
}
