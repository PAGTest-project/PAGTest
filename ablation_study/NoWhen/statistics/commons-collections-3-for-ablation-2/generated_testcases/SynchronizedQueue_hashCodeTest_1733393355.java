
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Queue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SynchronizedQueue_hashCodeTest {

    @Test
    public void testHashCode() {
        // Given
        Queue<String> mockQueue = mock(Queue.class);
        when(mockQueue.hashCode()).thenReturn(42);
        SynchronizedQueue<String> synchronizedQueue = new SynchronizedQueue<>(mockQueue);

        // When
        int result = synchronizedQueue.hashCode();

        // Then
        assertEquals(42, result);
    }
}
