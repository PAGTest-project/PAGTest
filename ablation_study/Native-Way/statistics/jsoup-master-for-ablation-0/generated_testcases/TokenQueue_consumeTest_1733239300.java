
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sampleQueueData");
    }

    @Test
    public void testConsumeValidSequence() {
        tokenQueue = new TokenQueue("sampleQueueData");
        tokenQueue.consume("sample");
        assertEquals(6, tokenQueue.pos);
    }

    @Test
    public void testConsumeInvalidSequence() {
        tokenQueue = new TokenQueue("sampleQueueData");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            tokenQueue.consume("invalid");
        });
        assertEquals("Queue did not match expected sequence", exception.getMessage());
    }

    @Test
    public void testConsumeLongerThanRemaining() {
        tokenQueue = new TokenQueue("short");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            tokenQueue.consume("longerThanRemaining");
        });
        assertEquals("Queue not long enough to consume sequence", exception.getMessage());
    }
}
