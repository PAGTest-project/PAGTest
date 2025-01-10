
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sample data for testing");
    }

    @Test
    public void testChompToWithExistingSequence() {
        tokenQueue = new TokenQueue("sample data for testing");
        String result = tokenQueue.chompTo("for");
        assertEquals("sample data ", result);
        assertEquals("testing", tokenQueue.remainder());
    }

    @Test
    public void testChompToWithNonExistingSequence() {
        tokenQueue = new TokenQueue("sample data for testing");
        String result = tokenQueue.chompTo("nonexistent");
        assertEquals("sample data for testing", result);
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testChompToWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.chompTo("any");
        assertEquals("", result);
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testChompToWithSequenceAtStart() {
        tokenQueue = new TokenQueue("start data for testing");
        String result = tokenQueue.chompTo("start");
        assertEquals("", result);
        assertEquals("data for testing", tokenQueue.remainder());
    }

    @Test
    public void testChompToWithSequenceAtEnd() {
        tokenQueue = new TokenQueue("sample data for end");
        String result = tokenQueue.chompTo("end");
        assertEquals("sample data for ", result);
        assertTrue(tokenQueue.isEmpty());
    }
}
