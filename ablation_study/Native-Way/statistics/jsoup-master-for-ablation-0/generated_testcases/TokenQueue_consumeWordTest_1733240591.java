
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
        String word = tokenQueue.consumeWord();
        assertEquals("abc123", word);
        assertEquals("def456", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWordEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String word = tokenQueue.consumeWord();
        assertEquals("", word);
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testConsumeWordNoWord() {
        tokenQueue = new TokenQueue("!@#$%^&*()");
        String word = tokenQueue.consumeWord();
        assertEquals("", word);
        assertEquals("!@#$%^&*()", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWordMixedCharacters() {
        tokenQueue = new TokenQueue("abc123!@#def456");
        String word = tokenQueue.consumeWord();
        assertEquals("abc123", word);
        assertEquals("!@#def456", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWordMultipleWords() {
        tokenQueue = new TokenQueue("abc123 def456 ghi789");
        String word1 = tokenQueue.consumeWord();
        assertEquals("abc123", word1);
        tokenQueue.consumeWhitespace();
        String word2 = tokenQueue.consumeWord();
        assertEquals("def456", word2);
        tokenQueue.consumeWhitespace();
        String word3 = tokenQueue.consumeWord();
        assertEquals("ghi789", word3);
        assertTrue(tokenQueue.isEmpty());
    }
}
