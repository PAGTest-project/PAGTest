
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Queue;

public class SynchronizedQueue_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        SynchronizedQueue<String> queue = new SynchronizedQueue<>(mock(Queue.class));
        assertTrue(queue.equals(queue));
    }

    @Test
    public void testEquals_DifferentInstance() {
        Queue<String> mockQueue = mock(Queue.class);
        SynchronizedQueue<String> queue = new SynchronizedQueue<>(mockQueue);
        SynchronizedQueue<String> anotherQueue = new SynchronizedQueue<>(mockQueue);

        when(mockQueue.equals(any(Queue.class))).thenReturn(true);

        assertTrue(queue.equals(anotherQueue));
    }
}
