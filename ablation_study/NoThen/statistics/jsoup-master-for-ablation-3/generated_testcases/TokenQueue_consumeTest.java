
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("One Two");
    }

    @Test
    public void testConsumeValidSequence() {
        tokenQueue.addFirst("One");
        tokenQueue.consume("One");
        assertEquals(" Two", tokenQueue.remainder());
    }

    @Test
    public void testConsumeInvalidSequence() {
        assertThrows(IllegalStateException.class, () -> tokenQueue.consume("Three"));
    }

    @Test
    public void testConsumeLongerThanRemaining() {
        tokenQueue.addFirst("One");
        assertThrows(IllegalStateException.class, () -> tokenQueue.consume("One Two Three"));
    }

    @Test
    public void testConsumeEmptyQueue() {
        TokenQueue emptyQueue = new TokenQueue("");
        assertThrows(IllegalStateException.class, () -> emptyQueue.consume("One"));
    }

    @Test
    public void testConsumeAfterConsumeTo() {
        tokenQueue.consumeTo("Two");
        tokenQueue.consume("Two");
        assertEquals("", tokenQueue.remainder());
    }

    @Test
    public void testConsumeAfterConsumeWhitespace() {
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("One");
        assertEquals("Two", tokenQueue.remainder());
    }
}
