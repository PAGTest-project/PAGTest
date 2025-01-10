
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Queue;

public class SynchronizedQueue_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        Queue<String> queue = new ArrayDeque<>();
        SynchronizedQueue<String> synchronizedQueue = SynchronizedQueue.synchronizedQueue(queue);

        assertTrue(synchronizedQueue.equals(synchronizedQueue));
    }

    @Test
    public void testEquals_DifferentInstancesWithSameContent() {
        Queue<String> queue1 = new ArrayDeque<>();
        queue1.add("element");
        SynchronizedQueue<String> synchronizedQueue1 = SynchronizedQueue.synchronizedQueue(queue1);

        Queue<String> queue2 = new ArrayDeque<>();
        queue2.add("element");
        SynchronizedQueue<String> synchronizedQueue2 = SynchronizedQueue.synchronizedQueue(queue2);

        assertTrue(synchronizedQueue1.equals(synchronizedQueue2));
    }

    @Test
    public void testEquals_DifferentInstancesWithDifferentContent() {
        Queue<String> queue1 = new ArrayDeque<>();
        queue1.add("element1");
        SynchronizedQueue<String> synchronizedQueue1 = SynchronizedQueue.synchronizedQueue(queue1);

        Queue<String> queue2 = new ArrayDeque<>();
        queue2.add("element2");
        SynchronizedQueue<String> synchronizedQueue2 = SynchronizedQueue.synchronizedQueue(queue2);

        assertFalse(synchronizedQueue1.equals(synchronizedQueue2));
    }
}
