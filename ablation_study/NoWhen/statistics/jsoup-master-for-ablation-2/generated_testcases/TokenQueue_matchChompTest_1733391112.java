
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchChompTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sample(data)for(testing)");
    }

    @Test
    public void testMatchChompSuccess() {
        assertTrue(tokenQueue.matchChomp("sample"));
        assertEquals("(data)for(testing)", tokenQueue.toString());
    }

    @Test
    public void testMatchChompFailure() {
        assertFalse(tokenQueue.matchChomp("invalid"));
        assertEquals("sample(data)for(testing)", tokenQueue.toString());
    }

    @Test
    public void testMatchChompEmptyQueue() {
        tokenQueue = new TokenQueue("");
        assertFalse(tokenQueue.matchChomp("anything"));
    }

    @Test
    public void testMatchChompPartialMatch() {
        assertFalse(tokenQueue.matchChomp("sample(data"));
        assertEquals("sample(data)for(testing)", tokenQueue.toString());
    }

    @Test
    public void testMatchChompCaseInsensitive() {
        assertTrue(tokenQueue.matchChomp("SAMPLE"));
        assertEquals("(data)for(testing)", tokenQueue.toString());
    }
}
