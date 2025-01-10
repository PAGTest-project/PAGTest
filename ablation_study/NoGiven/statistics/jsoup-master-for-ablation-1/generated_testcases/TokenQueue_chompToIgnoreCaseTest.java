
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToIgnoreCaseTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("TestStringForChompToIgnoreCase");
    }

    @Test
    public void testChompToIgnoreCaseMatchFound() {
        String result = tokenQueue.chompToIgnoreCase("string");
        assertEquals("Test", result);
    }

    @Test
    public void testChompToIgnoreCaseMatchNotFound() {
        String result = tokenQueue.chompToIgnoreCase("nonexistent");
        assertEquals("TestStringForChompToIgnoreCase", result);
    }

    @Test
    public void testChompToIgnoreCaseEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.chompToIgnoreCase("any");
        assertEquals("", result);
    }

    @Test
    public void testChompToIgnoreCaseCaseInsensitiveMatch() {
        String result = tokenQueue.chompToIgnoreCase("STRING");
        assertEquals("Test", result);
    }

    @Test
    public void testChompToIgnoreCaseMatchAtStart() {
        String result = tokenQueue.chompToIgnoreCase("test");
        assertEquals("", result);
    }

    @Test
    public void testChompToIgnoreCaseMatchAtEnd() {
        String result = tokenQueue.chompToIgnoreCase("IgnoreCase");
        assertEquals("TestStringForChompTo", result);
    }
}
