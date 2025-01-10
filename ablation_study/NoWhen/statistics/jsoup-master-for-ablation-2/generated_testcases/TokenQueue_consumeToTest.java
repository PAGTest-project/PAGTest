
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("example text for testing");
    }

    @Test
    public void testConsumeToWithMatch() {
        String result = tokenQueue.consumeTo("text");
        assertEquals("example ", result);
        assertEquals(8, tokenQueue.pos);
    }

    @Test
    public void testConsumeToWithoutMatch() {
        String result = tokenQueue.consumeTo("nonexistent");
        assertEquals("example text for testing", result);
        assertEquals(24, tokenQueue.pos);
    }

    @Test
    public void testConsumeToWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeTo("text");
        assertEquals("", result);
        assertEquals(0, tokenQueue.pos);
    }

    @Test
    public void testConsumeToWithExactMatch() {
        tokenQueue = new TokenQueue("text");
        String result = tokenQueue.consumeTo("text");
        assertEquals("", result);
        assertEquals(0, tokenQueue.pos);
    }

    @Test
    public void testConsumeToWithMultipleMatches() {
        tokenQueue = new TokenQueue("text text text");
        String result = tokenQueue.consumeTo("text");
        assertEquals("", result);
        assertEquals(0, tokenQueue.pos);
    }
}
