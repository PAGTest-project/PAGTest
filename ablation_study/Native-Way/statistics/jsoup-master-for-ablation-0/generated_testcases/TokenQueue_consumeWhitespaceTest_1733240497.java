
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeWhitespaceTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("  \t\nHello World  ");
    }

    @Test
    public void testConsumeWhitespace_WithWhitespace() {
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("Hello World  ", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_WithoutWhitespace() {
        tokenQueue = new TokenQueue("HelloWorld");
        assertFalse(tokenQueue.consumeWhitespace());
        assertEquals("HelloWorld", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.consumeWhitespace());
        assertEquals("", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespace_MixedCharacters() {
        tokenQueue = new TokenQueue("  \t\nHello  \t\nWorld  \t\n");
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("Hello  \t\nWorld  \t\n", tokenQueue.remainder());
    }
}
