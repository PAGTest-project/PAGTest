
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import java.util.Queue;
import java.util.LinkedList;

public class SynchronizedQueue_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        Queue<String> queue = new LinkedList<>();
        SynchronizedQueue<String> synchronizedQueue = SynchronizedQueue.synchronizedQueue(queue);

        assertTrue(synchronizedQueue.equals(synchronizedQueue));
    }

    @Test
    public void testEquals_DifferentInstancesWithSameElements() {
        Queue<String> queue1 = new LinkedList<>();
        queue1.offer("element1");
        queue1.offer("element2");
        SynchronizedQueue<String> synchronizedQueue1 = SynchronizedQueue.synchronizedQueue(queue1);

        Queue<String> queue2 = new LinkedList<>();
        queue2.offer("element1");
        queue2.offer("element2");
        SynchronizedQueue<String> synchronizedQueue2 = SynchronizedQueue.synchronizedQueue(queue2);

        assertTrue(synchronizedQueue1.equals(synchronizedQueue2));
    }

    @Test
    public void testEquals_DifferentInstancesWithDifferentElements() {
        Queue<String> queue1 = new LinkedList<>();
        queue1.offer("element1");
        SynchronizedQueue<String> synchronizedQueue1 = SynchronizedQueue.synchronizedQueue(queue1);

        Queue<String> queue2 = new LinkedList<>();
        queue2.offer("element2");
        SynchronizedQueue<String> synchronizedQueue2 = SynchronizedQueue.synchronizedQueue(queue2);

        assertFalse(synchronizedQueue1.equals(synchronizedQueue2));
    }
}
