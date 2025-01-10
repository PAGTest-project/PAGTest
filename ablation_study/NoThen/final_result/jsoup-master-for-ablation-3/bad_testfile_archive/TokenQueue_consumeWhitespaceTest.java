
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
    public void testConsumeWhitespace_WithLeadingWhitespace() {
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("One Two", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_WithoutLeadingWhitespace() {
        tokenQueue = new TokenQueue("One Two");
        assertFalse(tokenQueue.consumeWhitespace());
        assertEquals("One Two", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_WithOnlyWhitespace() {
        tokenQueue = new TokenQueue("  \t\n");
        assertTrue(tokenQueue.consumeWhitespace());
        assertTrue(tokenQueue.remainder().isEmpty());
    }

    @Test
    public void testConsumeWhitespace_AfterConsumingWord() {
        tokenQueue.consumeWord();
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("Two", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_AfterConsumingTo() {
        tokenQueue.consumeTo("Two");
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("Two", tokenQueue.remainder());
    }
}
