
package com.github.davidmoten.rtree.internal.util;

import static com.github.davidmoten.rtree.internal.util.BoundedPriorityQueue.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

public class BoundedPriorityQueue_createTest {

    private static final Comparator<Integer> comparator = Comparator.naturalOrder();

    @Test
    public void testCreateWithValidParameters() {
        BoundedPriorityQueue<Integer> queue = create(5, comparator);
        assertEquals(5, queue.maxSize);
        assertTrue(queue.asList().isEmpty());
    }
}
