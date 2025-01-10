
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToIgnoreCaseTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("TestString");
    }

    @Test
    public void testChompToIgnoreCase() {
        tokenQueue = new TokenQueue("TestString");
        String result = tokenQueue.chompToIgnoreCase("string");
        assertEquals("Test", result);
        assertEquals("String", tokenQueue.remainder());
    }

    @Test
    public void testChompToIgnoreCaseNoMatch() {
        tokenQueue = new TokenQueue("TestString");
        String result = tokenQueue.chompToIgnoreCase("xyz");
        assertEquals("TestString", result);
        assertEquals("", tokenQueue.remainder());
    }
}
