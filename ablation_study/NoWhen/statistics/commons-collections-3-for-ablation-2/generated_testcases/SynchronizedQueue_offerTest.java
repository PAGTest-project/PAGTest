
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Queue;
import org.junit.jupiter.api.Test;

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
    }
}
