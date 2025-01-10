
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
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("world456", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordWithLeadingWhitespace() {
        tokenQueue = new TokenQueue("   leadingWhitespace123");
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("leadingWhitespace123", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordWithNoWordCharacters() {
        tokenQueue = new TokenQueue("   ");
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertEquals("", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }
}
