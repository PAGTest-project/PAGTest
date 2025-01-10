
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Queue;

public class SynchronizedQueue_hashCodeTest {

    @Test
    public void testHashCode() {
        // Given
        Queue<String> mockQueue = mock(Queue.class);
        Object lock = new Object();
        SynchronizedQueue<String> synchronizedQueue = new SynchronizedQueue<>(mockQueue, lock);

        // When
        doReturn(42).when(mockQueue).hashCode();

        // Then
        int result = synchronizedQueue.hashCode();
        assertEquals(42, result);
    }
}
