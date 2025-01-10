
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeWordTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("Hello123 World456");
    }

    @Test
    public void testConsumeWord() {
        assertEquals("Hello123", tokenQueue.consumeWord());
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("World456", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordWithNoWords() {
        tokenQueue = new TokenQueue("   ");
        assertEquals("", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordWithMixedCharacters() {
        tokenQueue = new TokenQueue("Hello123!@#World456");
        assertEquals("Hello123", tokenQueue.consumeWord());
        assertEquals("!@#World456", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWordWithEscapedCharacters() {
        tokenQueue = new TokenQueue("Hello\\123 World\\456");
        assertEquals("Hello\\123", tokenQueue.consumeWord());
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("World\\456", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }
}
