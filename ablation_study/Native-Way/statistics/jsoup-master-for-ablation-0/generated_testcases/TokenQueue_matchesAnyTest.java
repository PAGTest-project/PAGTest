
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchesAnyTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sampleQueue");
    }

    @Test
    public void testMatchesAnyWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.matchesAny('a', 'b', 'c'));
    }

    @Test
    public void testMatchesAnyWithMatchingChar() {
        assertTrue(tokenQueue.matchesAny('s', 'a', 'm'));
    }

    @Test
    public void testMatchesAnyWithNonMatchingChar() {
        assertFalse(tokenQueue.matchesAny('x', 'y', 'z'));
    }

    @Test
    public void testMatchesAnyWithMultipleChars() {
        tokenQueue = new TokenQueue("abc");
        assertTrue(tokenQueue.matchesAny('a', 'b', 'c'));
        assertFalse(tokenQueue.matchesAny('x', 'y', 'z'));
    }

    @Test
    public void testMatchesAnyWithSingleCharQueue() {
        tokenQueue = new TokenQueue("a");
        assertTrue(tokenQueue.matchesAny('a'));
        assertFalse(tokenQueue.matchesAny('b'));
    }

    @Test
    public void testMatchesAnyWithNoChars() {
        assertFalse(tokenQueue.matchesAny());
    }
}
