
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeWordTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("hello123 world456");
    }

    @Test
    public void testConsumeWord() {
        assertEquals("hello123", tokenQueue.consumeWord());
        assertEquals(" ", tokenQueue.remainder()); // Ensure the word was consumed
        assertEquals("world456", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty()); // Ensure the queue is empty after consuming the word
    }
}
