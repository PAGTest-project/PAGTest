
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
    public void testMatchChomp_MatchFound() {
        assertTrue(tokenQueue.matchChomp("test"));
        // Remove the assertion for pos since it is private
    }

    @Test
    public void testMatchChomp_MatchNotFound() {
        assertFalse(tokenQueue.matchChomp("invalid"));
        // Remove the assertion for pos since it is private
    }
}
