
package org.apache.commons.collections4.queue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SynchronizedQueue_elementTest {

    @Test
    public void testElement() {
        // Given
        Queue<String> mockQueue = Mockito.mock(Queue.class);
        Object lock = new Object();
        SynchronizedQueue<String> synchronizedQueue = new SynchronizedQueue<>(mockQueue, lock);
        String expectedElement = "testElement";
        when(mockQueue.element()).thenReturn(expectedElement);

        // When
        String actualElement = synchronizedQueue.element();

        // Then
        assertEquals(expectedElement, actualElement);
    }
}
