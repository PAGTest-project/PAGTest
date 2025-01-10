
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("testString123");
    }

    @Test
    public void testConsumeToWithMatch() {
        tokenQueue.addFirst("test");
        assertEquals("test", tokenQueue.consumeTo("String"));
    }

    @Test
    public void testConsumeToWithoutMatch() {
        assertEquals("testString123", tokenQueue.consumeTo("nonexistent"));
    }

    @Test
    public void testConsumeToWithWhitespaceConsumed() {
        tokenQueue = new TokenQueue("  testString123");
        tokenQueue.consumeWhitespace();
        assertEquals("test", tokenQueue.consumeTo("String"));
    }

    @Test
    public void testConsumeToWithIgnoreCase() {
        tokenQueue = new TokenQueue("teststring123");
        assertEquals("test", tokenQueue.consumeToIgnoreCase("String"));
    }

    @Test
    public void testConsumeToWithAny() {
        tokenQueue = new TokenQueue("testString123");
        assertEquals("test", tokenQueue.consumeToAny("String", "123"));
    }
}
