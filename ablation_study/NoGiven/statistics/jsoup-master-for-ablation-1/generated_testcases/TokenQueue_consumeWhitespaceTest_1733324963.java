
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeWhitespaceTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("  \t\n  test");
    }

    @Test
    public void testConsumeWhitespaceWithWhitespace() {
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("test", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespaceWithoutWhitespace() {
        tokenQueue = new TokenQueue("test");
        assertFalse(tokenQueue.consumeWhitespace());
        assertEquals("test", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespaceWithMixedCharacters() {
        tokenQueue = new TokenQueue("  \t\n  test  \t\n  ");
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("test  \t\n  ", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespaceWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.consumeWhitespace());
        assertEquals("", tokenQueue.remainder());
    }
}
