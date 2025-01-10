
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
        when(mockQueue.hashCode()).thenReturn(42);
        SynchronizedQueue<String> synchronizedQueue = new SynchronizedQueue<>(mockQueue, new Object());

        // When
        int result = synchronizedQueue.hashCode();

        // Then
        assertEquals(42, result);
    }
}
