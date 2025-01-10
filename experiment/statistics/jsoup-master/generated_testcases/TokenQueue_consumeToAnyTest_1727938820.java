
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
        tokenQueue.pos = 0;
        String result = tokenQueue.consumeToAny("for", "testing");
        assertEquals("sample text ", result);
    }

    @Test
    public void testConsumeToAnyWithoutMatch() {
        tokenQueue.pos = 0;
        String result = tokenQueue.consumeToAny("nonexistent");
        assertEquals("sample text for testing", result);
    }

    @Test
    public void testConsumeToAnyWithEmptyQueue() {
        tokenQueue.pos = tokenQueue.queue.length();
        String result = tokenQueue.consumeToAny("for", "testing");
        assertEquals("", result);
    }
}
