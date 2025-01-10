
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sample data sequence");
    }

    @Test
    public void testChompToWithExistingSequence() {
        String result = tokenQueue.chompTo("sequence");
        assertEquals("sample data ", result);
        assertEquals("sequence", tokenQueue.remainder());
    }

    @Test
    public void testChompToWithNonExistingSequence() {
        String result = tokenQueue.chompTo("nonexistent");
        assertEquals("sample data sequence", result);
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testChompToWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.chompTo("sequence");
        assertEquals("", result);
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testChompToWithSequenceAtStart() {
        tokenQueue = new TokenQueue("sequence sample data");
        String result = tokenQueue.chompTo("sequence");
        assertEquals("", result);
        assertEquals("sequence sample data", tokenQueue.remainder());
    }

    @Test
    public void testChompToWithSequenceAtEnd() {
        tokenQueue = new TokenQueue("sample data sequence");
        String result = tokenQueue.chompTo("sequence");
        assertEquals("sample data ", result);
        assertEquals("sequence", tokenQueue.remainder());
    }
}
