
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
    public void testMatchesAnyWithMatchingCharacter() {
        assertTrue(tokenQueue.matchesAny('a', 'b', 'c'));
    }

    @Test
    public void testMatchesAnyWithNonMatchingCharacter() {
        assertFalse(tokenQueue.matchesAny('x', 'y', 'z'));
    }
}
