
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("initial data");
    }

    @Test
    public void testChompToWithMatch() {
        tokenQueue = new TokenQueue("startmatchend");
        String result = tokenQueue.chompTo("match");
        assertEquals("start", result);
        assertEquals("end", tokenQueue.remainder());
    }

    @Test
    public void testChompToWithoutMatch() {
        tokenQueue = new TokenQueue("startend");
        String result = tokenQueue.chompTo("match");
        assertEquals("startend", result);
        assertTrue(tokenQueue.isEmpty());
    }
}
