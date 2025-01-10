
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchesAnyTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("abc");
    }

    @Test
    public void testMatchesAnyWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.matchesAny('a', 'b', 'c'));
    }

    @Test
    public void testMatchesAnyWithMatchingChar() {
        assertTrue(tokenQueue.matchesAny('a', 'b', 'c'));
    }

    @Test
    public void testMatchesAnyWithNonMatchingChar() {
        assertFalse(tokenQueue.matchesAny('x', 'y', 'z'));
    }

    @Test
    public void testMatchesAnyWithMixedChars() {
        assertTrue(tokenQueue.matchesAny('x', 'y', 'a'));
    }

    @Test
    public void testMatchesAnyAfterConsumeToAny() {
        tokenQueue = new TokenQueue("abcde");
        tokenQueue.consumeToAny("b", "d");
        assertTrue(tokenQueue.matchesAny('b', 'd'));
    }

    @Test
    public void testMatchesAnyAfterConsumeWhitespace() {
        tokenQueue = new TokenQueue("  abc");
        tokenQueue.consumeWhitespace();
        assertTrue(tokenQueue.matchesAny('a', 'b', 'c'));
    }
}
