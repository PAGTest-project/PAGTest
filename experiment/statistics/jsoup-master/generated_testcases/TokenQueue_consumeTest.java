
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("validsequence");
    }

    @Test
    public void testConsumeValidSequence() {
        tokenQueue.consume("valid");
        assertEquals(5, tokenQueue.pos);
    }

    @Test
    public void testConsumeInvalidSequence() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            tokenQueue.consume("invalid");
        });
        assertEquals("Queue did not match expected sequence", exception.getMessage());
    }

    @Test
    public void testConsumeSequenceTooLong() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            tokenQueue.consume("validsequenceandmore");
        });
        assertEquals("Queue not long enough to consume sequence", exception.getMessage());
    }
}
