
package com.github.davidmoten.rtree.internal.util;

import static com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BoundedPriorityQueue_addTest {

    private static final Comparator<Integer> comparator = Comparator.naturalOrder();
    private BoundedPriorityQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = create(3, comparator);
    }

    @Test
    public void testAddNullThrowsException() {
        assertThrows(NullPointerException.class, () -> queue.add(null));
    }

    @Test
    public void testAddElementWhenQueueIsNotFull() {
        queue.add(1);
        List<Integer> list = queue.asList();
        assertEquals(1, list.size());
        assertTrue(list.contains(1));
    }

    @Test
    public void testAddElementWhenQueueIsFullAndNewElementIsGreater() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        List<Integer> list = queue.asList();
        assertEquals(3, list.size());
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));
    }

    @Test
    public void testAddElementWhenQueueIsFullAndNewElementIsLess() {
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(1);
        List<Integer> list = queue.asList();
        assertEquals(3, list.size());
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));
    }

    @Test
    public void testAddElementWhenQueueIsFullAndNewElementIsEqual() {
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(3);
        List<Integer> list = queue.asList();
        assertEquals(3, list.size());
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));
        assertTrue(list.contains(3));
    }
}
