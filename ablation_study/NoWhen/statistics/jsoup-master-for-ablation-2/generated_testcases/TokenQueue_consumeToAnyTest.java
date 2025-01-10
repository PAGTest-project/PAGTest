
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
        String result = tokenQueue.consumeToAny("nonexistent", "sequence");
        assertEquals("sample text for testing", result);
    }

    @Test
    public void testConsumeToAnyWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeToAny("text", "for");
        assertEquals("", result);
    }

    @Test
    public void testConsumeToAnyWithSingleCharacterMatch() {
        tokenQueue = new TokenQueue("a simple test");
        String result = tokenQueue.consumeToAny(" ", "t");
        assertEquals("a", result);
    }

    @Test
    public void testConsumeToAnyWithMultipleMatches() {
        tokenQueue = new TokenQueue("multiple matches here");
        String result = tokenQueue.consumeToAny("matches", "here");
        assertEquals("multiple ", result);
    }
}
