
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeWhitespaceTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("  \t\nOne Two");
    }

    @Test
    public void testConsumeWhitespace_WithWhitespace() {
        assertTrue(tokenQueue.consumeWhitespace());
        assertFalse(tokenQueue.matchesWhitespace());
        assertEquals("One Two", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_WithoutWhitespace() {
        tokenQueue = new TokenQueue("One Two");
        assertFalse(tokenQueue.consumeWhitespace());
        assertEquals("One Two", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.consumeWhitespace());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWhitespace_OnlyWhitespace() {
        tokenQueue = new TokenQueue("  \t\n");
        assertTrue(tokenQueue.consumeWhitespace());
        assertTrue(tokenQueue.isEmpty());
    }
}
