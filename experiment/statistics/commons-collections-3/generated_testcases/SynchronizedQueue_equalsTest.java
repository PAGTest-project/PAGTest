
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Queue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SynchronizedQueue_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        Queue<String> mockQueue = mock(Queue.class);
        SynchronizedQueue<String> synchronizedQueue = new SynchronizedQueue<>(mockQueue);

        assertTrue(synchronizedQueue.equals(synchronizedQueue));
    }

    @Test
    public void testEquals_DifferentInstance() {
        Queue<String> mockQueue1 = mock(Queue.class);
        Queue<String> mockQueue2 = mock(Queue.class);
        SynchronizedQueue<String> synchronizedQueue1 = new SynchronizedQueue<>(mockQueue1);
        SynchronizedQueue<String> synchronizedQueue2 = new SynchronizedQueue<>(mockQueue2);

        when(mockQueue1.equals(Mockito.any())).thenReturn(true);

        assertTrue(synchronizedQueue1.equals(synchronizedQueue2));
    }

    @Test
    public void testEquals_DifferentInstanceNotEqual() {
        Queue<String> mockQueue1 = mock(Queue.class);
        Queue<String> mockQueue2 = mock(Queue.class);
        SynchronizedQueue<String> synchronizedQueue1 = new SynchronizedQueue<>(mockQueue1);
        SynchronizedQueue<String> synchronizedQueue2 = new SynchronizedQueue<>(mockQueue2);

        when(mockQueue1.equals(Mockito.any())).thenReturn(false);

        assertFalse(synchronizedQueue1.equals(synchronizedQueue2));
    }
}
