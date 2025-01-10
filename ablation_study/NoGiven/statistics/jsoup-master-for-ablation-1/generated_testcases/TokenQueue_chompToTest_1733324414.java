
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("example data sequence");
    }

    @Test
    public void testChompToWithExistingSequence() {
        tokenQueue = new TokenQueue("example data sequence");
        String result = tokenQueue.chompTo("sequence");
        assertEquals("example data ", result);
        assertEquals("sequence", tokenQueue.remainder());
    }

    @Test
    public void testChompToWithNonExistingSequence() {
        tokenQueue = new TokenQueue("example data sequence");
        String result = tokenQueue.chompTo("nonexistent");
        assertEquals("example data sequence", result);
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
        tokenQueue = new TokenQueue("sequence example data");
        String result = tokenQueue.chompTo("sequence");
        assertEquals("", result);
        assertEquals("sequence example data", tokenQueue.remainder());
    }

    @Test
    public void testChompToWithSequenceAtEnd() {
        tokenQueue = new TokenQueue("example data sequence");
        String result = tokenQueue.chompTo("sequence");
        assertEquals("example data ", result);
        assertEquals("sequence", tokenQueue.remainder());
    }
}
