
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchesAnyTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("One Two");
    }

    @Test
    public void testMatchesAnyWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.matchesAny('O', 'T'));
    }

    @Test
    public void testMatchesAnyWithMatchingChar() {
        assertTrue(tokenQueue.matchesAny('O', 'T'));
    }

    @Test
    public void testMatchesAnyWithNonMatchingChar() {
        assertFalse(tokenQueue.matchesAny('X', 'Y'));
    }

    @Test
    public void testMatchesAnyWithMultipleChars() {
        assertTrue(tokenQueue.matchesAny('O', 'N', 'E'));
    }

    @Test
    public void testMatchesAnyWithNoChars() {
        assertFalse(tokenQueue.matchesAny());
    }
}
