
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToIgnoreCaseTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("TestStringForConsumeToIgnoreCase");
    }

    @Test
    public void testConsumeToIgnoreCase_MatchFound() {
        String result = tokenQueue.consumeToIgnoreCase("string");
        assertEquals("Test", result);
    }

    @Test
    public void testConsumeToIgnoreCase_NoMatchFound() {
        String result = tokenQueue.consumeToIgnoreCase("nonexistent");
        assertEquals("TestStringForConsumeToIgnoreCase", result);
    }

    @Test
    public void testConsumeToIgnoreCase_CaseInsensitiveMatch() {
        String result = tokenQueue.consumeToIgnoreCase("STRING");
        assertEquals("Test", result);
    }

    @Test
    public void testConsumeToIgnoreCase_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeToIgnoreCase("anything");
        assertEquals("", result);
    }
}
