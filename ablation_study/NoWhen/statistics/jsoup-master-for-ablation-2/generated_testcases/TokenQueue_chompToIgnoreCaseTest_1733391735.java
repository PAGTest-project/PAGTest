
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
    public void testChompToIgnoreCaseCaseInsensitiveMatch() {
        String result = tokenQueue.chompToIgnoreCase("STRING");
        assertEquals("Test", result);
    }

    @Test
    public void testChompToIgnoreCaseEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.chompToIgnoreCase("string");
        assertEquals("", result);
    }

    @Test
    public void testChompToIgnoreCaseMatchAtStart() {
        tokenQueue = new TokenQueue("stringAtStartTest");
        String result = tokenQueue.chompToIgnoreCase("string");
        assertEquals("", result);
    }

    @Test
    public void testChompToIgnoreCaseMatchAtEnd() {
        tokenQueue = new TokenQueue("TestStringAtEnd");
        String result = tokenQueue.chompToIgnoreCase("end");
        assertEquals("TestStringAt", result);
    }
}
