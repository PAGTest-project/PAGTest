
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToIgnoreCaseTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("One Two");
    }

    @Test
    public void testChompToIgnoreCase_MatchFound() {
        tokenQueue = new TokenQueue("One Two");
        String result = tokenQueue.chompToIgnoreCase("two");
        assertEquals("One ", result);
        assertEquals("Two", tokenQueue.remainder());
    }

    @Test
    public void testChompToIgnoreCase_NoMatchFound() {
        tokenQueue = new TokenQueue("One Two");
        String result = tokenQueue.chompToIgnoreCase("three");
        assertEquals("One Two", result);
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testChompToIgnoreCase_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.chompToIgnoreCase("two");
        assertEquals("", result);
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testChompToIgnoreCase_CaseInsensitiveMatch() {
        tokenQueue = new TokenQueue("One tWo");
        String result = tokenQueue.chompToIgnoreCase("two");
        assertEquals("One ", result);
        assertEquals("tWo", tokenQueue.remainder());
    }

    @Test
    public void testChompToIgnoreCase_MultipleMatches() {
        tokenQueue = new TokenQueue("One Two Three Two");
        String result = tokenQueue.chompToIgnoreCase("two");
        assertEquals("One ", result);
        assertEquals("Two Three Two", tokenQueue.remainder());
    }
}
