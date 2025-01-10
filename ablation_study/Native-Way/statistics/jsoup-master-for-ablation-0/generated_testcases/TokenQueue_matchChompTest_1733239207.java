
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchChompTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("One Two");
    }

    @Test
    public void testMatchChomp_MatchFound() {
        assertTrue(tokenQueue.matchChomp("One"));
        assertEquals(" Two", tokenQueue.remainder());
    }

    @Test
    public void testMatchChomp_MatchNotFound() {
        assertFalse(tokenQueue.matchChomp("Three"));
        assertEquals("One Two", tokenQueue.remainder());
    }

    @Test
    public void testMatchChomp_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.matchChomp("One"));
        assertEquals("", tokenQueue.remainder());
    }

    @Test
    public void testMatchChomp_PartialMatch() {
        assertFalse(tokenQueue.matchChomp("One "));
        assertEquals("One Two", tokenQueue.remainder());
    }

    @Test
    public void testMatchChomp_ExactMatch() {
        assertTrue(tokenQueue.matchChomp("One Two"));
        assertEquals("", tokenQueue.remainder());
    }

    @Test
    public void testMatchChomp_CaseInsensitiveMatch() {
        assertFalse(tokenQueue.matchChomp("one two"));
        assertEquals("One Two", tokenQueue.remainder());
    }

    @Test
    public void testMatchChomp_MultipleMatches() {
        assertTrue(tokenQueue.matchChomp("One"));
        assertTrue(tokenQueue.matchChomp(" Two"));
        assertEquals("", tokenQueue.remainder());
    }

    @Test
    public void testMatchChomp_MatchAtEnd() {
        tokenQueue = new TokenQueue("One Two Three");
        assertTrue(tokenQueue.matchChomp("Three"));
        assertEquals("", tokenQueue.remainder());
    }
}
