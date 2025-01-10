
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Queue;

public class SynchronizedQueue_offerTest {

    @Test
    public void testOffer() {
        // Given
        Queue<String> mockQueue = mock(Queue.class);
        SynchronizedQueue<String> synchronizedQueue = SynchronizedQueue.synchronizedQueue(mockQueue);
        String element = "testElement";

        // When
        when(mockQueue.offer(element)).thenReturn(true);
        boolean result = synchronizedQueue.offer(element);

        // Then
        assertTrue(result);
        verify(mockQueue, times(1)).offer(element);
    }
}
