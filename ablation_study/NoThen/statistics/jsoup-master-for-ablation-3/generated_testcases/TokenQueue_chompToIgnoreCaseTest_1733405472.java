
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
        tokenQueue.pos = 0;
        String result = tokenQueue.chompToIgnoreCase("string");
        assertEquals("Test", result);
        assertEquals(4, tokenQueue.pos);
    }

    @Test
    public void testChompToIgnoreCaseMatchNotFound() {
        tokenQueue.pos = 0;
        String result = tokenQueue.chompToIgnoreCase("nonexistent");
        assertEquals("TestStringForChompToIgnoreCase", result);
        assertEquals(28, tokenQueue.pos);
    }

    @Test
    public void testChompToIgnoreCaseCaseInsensitiveMatch() {
        tokenQueue.pos = 0;
        String result = tokenQueue.chompToIgnoreCase("STRING");
        assertEquals("Test", result);
        assertEquals(4, tokenQueue.pos);
    }

    @Test
    public void testChompToIgnoreCaseEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.chompToIgnoreCase("any");
        assertEquals("", result);
        assertEquals(0, tokenQueue.pos);
    }

    @Test
    public void testChompToIgnoreCaseWithEscapeCharacter() {
        tokenQueue = new TokenQueue("Test\\StringForChompToIgnoreCase");
        tokenQueue.pos = 0;
        String result = tokenQueue.chompToIgnoreCase("string");
        assertEquals("Test\\", result);
        assertEquals(5, tokenQueue.pos);
    }
}
