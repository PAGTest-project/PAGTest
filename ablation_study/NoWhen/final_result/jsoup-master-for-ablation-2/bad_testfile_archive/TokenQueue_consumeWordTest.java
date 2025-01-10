
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeWordTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("abc123def456");
    }

    @Test
    public void testConsumeWord() {
        assertEquals("abc123", tokenQueue.consumeWord());
        assertEquals("def456", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordWithWhitespace() {
        tokenQueue = new TokenQueue("abc123 def456");
        assertEquals("abc123", tokenQueue.consumeWord());
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("def456", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordEmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertEquals("", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordNoWordCharacter() {
        tokenQueue = new TokenQueue("!@#$%^&*()");
        assertEquals("", tokenQueue.consumeWord());
        assertFalse(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordMixedCharacters() {
        tokenQueue = new TokenQueue("abc123!@#def456");
        assertEquals("abc123", tokenQueue.consumeWord());
        assertEquals("", tokenQueue.consumeWord());
        assertEquals("def456", tokenQueue.consumeWord());
        assertTrue(tokenQueue.isEmpty());
    }
}
