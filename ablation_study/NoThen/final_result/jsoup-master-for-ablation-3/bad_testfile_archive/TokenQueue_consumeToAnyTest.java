
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToAnyTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sample text for testing");
    }

    @Test
    public void testConsumeToAnyWithMatch() {
        tokenQueue = new TokenQueue("sample text for testing");
        String result = tokenQueue.consumeToAny("text", "for");
        assertEquals("sample ", result);
    }

    @Test
    public void testConsumeToAnyWithoutMatch() {
        tokenQueue = new TokenQueue("sample text for testing");
        String result = tokenQueue.consumeToAny("nonexistent", "terminator");
        assertEquals("sample text for testing", result);
    }

    @Test
    public void testConsumeToAnyWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeToAny("text", "for");
        assertEquals("", result);
    }

    @Test
    public void testConsumeToAnyWithMultipleMatches() {
        tokenQueue = new TokenQueue("sample text for testing");
        String result = tokenQueue.consumeToAny("text", "for", "sample");
        assertEquals("", result);
    }

    @Test
    public void testConsumeToAnyWithPartialMatch() {
        tokenQueue = new TokenQueue("sample text for testing");
        String result = tokenQueue.consumeToAny("text", "for", "test");
        assertEquals("sample ", result);
    }
}
