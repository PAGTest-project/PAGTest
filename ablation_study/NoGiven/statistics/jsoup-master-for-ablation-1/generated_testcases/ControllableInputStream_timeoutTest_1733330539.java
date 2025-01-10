
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllableInputStream_timeoutTest {

    @Test
    void testTimeout() {
        // Given
        SimpleBufferedInput input = new SimpleBufferedInput(new byte[0]);
        ControllableInputStream stream = new ControllableInputStream(input, 100);
        long startTimeNanos = System.nanoTime();
        long timeoutMillis = 1000;

        // When
        ControllableInputStream result = stream.timeout(startTimeNanos, timeoutMillis);

        // Then
        assertEquals(startTimeNanos, stream.startTime);
        assertEquals(timeoutMillis * 1000000, stream.timeout);
        assertSame(stream, result);
    }
}
