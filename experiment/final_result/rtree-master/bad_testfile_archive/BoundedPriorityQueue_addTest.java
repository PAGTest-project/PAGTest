
package com.github.davidmoten.rtree.internal.util;

import static com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class BoundedPriorityQueue_addTest {

    private static final Comparator<Integer> comparator = Comparator.naturalOrder();
    private BoundedPriorityQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = create(2, comparator);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullThrowsException() {
        queue.add(null);
    }

    @Test
    public void testAddElementToEmptyQueue() {
        queue.add(1);
        assertEquals(1, queue.asList().size());
        assertTrue(queue.asList().contains(1));
    }

    @Test
    public void testAddElementToFullQueueWithLowerPriority() {
        queue.add(1);
        queue.add(2);
        queue.add(0); // This should not be added as it has lower priority than 1
        assertEquals(2, queue.asList().size());
        assertTrue(queue.asList().contains(1));
        assertTrue(queue.asList().contains(2));
    }

    @Test
    public void testAddElementToFullQueueWithHigherPriority() {
        queue.add(1);
        queue.add(2);
        queue.add(3); // This should replace 1 as it has higher priority
        assertEquals(2, queue.asList().size());
        assertTrue(queue.asList().contains(2));
        assertTrue(queue.asList().contains(3));
    }
}
