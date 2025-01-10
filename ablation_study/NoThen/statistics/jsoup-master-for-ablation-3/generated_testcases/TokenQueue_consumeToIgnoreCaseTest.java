
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToIgnoreCaseTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("HelloWorld123");
    }

    @Test
    public void testConsumeToIgnoreCase_MatchFound() {
        tokenQueue.pos = 0;
        String result = tokenQueue.consumeToIgnoreCase("WORLD");
        assertEquals("Hello", result);
        assertEquals(5, tokenQueue.pos);
    }

    @Test
    public void testConsumeToIgnoreCase_NoMatchFound() {
        tokenQueue.pos = 0;
        String result = tokenQueue.consumeToIgnoreCase("XYZ");
        assertEquals("HelloWorld123", result);
        assertEquals(11, tokenQueue.pos);
    }

    @Test
    public void testConsumeToIgnoreCase_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        tokenQueue.pos = 0;
        String result = tokenQueue.consumeToIgnoreCase("WORLD");
        assertEquals("", result);
        assertEquals(0, tokenQueue.pos);
    }

    @Test
    public void testConsumeToIgnoreCase_PartialMatch() {
        tokenQueue = new TokenQueue("HelloWorld123");
        tokenQueue.pos = 5;
        String result = tokenQueue.consumeToIgnoreCase("WORLD");
        assertEquals("", result);
        assertEquals(5, tokenQueue.pos);
    }

    @Test
    public void testConsumeToIgnoreCase_CaseInsensitiveMatch() {
        tokenQueue = new TokenQueue("HelloWorld123");
        tokenQueue.pos = 0;
        String result = tokenQueue.consumeToIgnoreCase("world");
        assertEquals("Hello", result);
        assertEquals(5, tokenQueue.pos);
    }
}
