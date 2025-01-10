
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchChompTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("test sequence");
    }

    @Test
    public void testMatchChompSuccess() {
        tokenQueue.consumeTo("sequence");
        assertTrue(tokenQueue.matchChomp("sequence"));
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testMatchChompFailure() {
        tokenQueue.consumeTo("sequence");
        assertFalse(tokenQueue.matchChomp("invalid"));
        assertFalse(tokenQueue.isEmpty());
    }

    @Test
    public void testMatchChompWithEmptyQueue() {
        tokenQueue.consumeTo("sequence");
        tokenQueue.matchChomp("sequence");
        assertFalse(tokenQueue.matchChomp("sequence"));
        assertTrue(tokenQueue.isEmpty());
    }

    @Test
    public void testMatchChompWithPartialMatch() {
        tokenQueue.consumeTo("sequence");
        assertFalse(tokenQueue.matchChomp("seq"));
        assertFalse(tokenQueue.isEmpty());
    }
}
