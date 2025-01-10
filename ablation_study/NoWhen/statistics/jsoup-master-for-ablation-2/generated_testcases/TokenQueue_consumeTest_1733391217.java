
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("One Two Three");
    }

    @Test
    public void testConsumeValidSequence() {
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("One");
        assertEquals(" Two Three", tokenQueue.remainder());
    }

    @Test
    public void testConsumeInvalidSequence() {
        assertThrows(IllegalStateException.class, () -> tokenQueue.consume("Four"));
    }

    @Test
    public void testConsumeSequenceLongerThanRemaining() {
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("One");
        assertThrows(IllegalStateException.class, () -> tokenQueue.consume("Two Three Four"));
    }

    @Test
    public void testConsumeWithWhitespace() {
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("One");
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("Two");
        assertEquals(" Three", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWord() {
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("One");
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("Two");
        tokenQueue.consumeWhitespace();
        tokenQueue.consume("Three");
        assertTrue(tokenQueue.isEmpty());
    }
}
