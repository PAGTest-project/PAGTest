
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
        assertEquals("(data)for(testing)", tokenQueue.remainder());
    }

    @Test
    public void testMatchChompFailure() {
        assertFalse(tokenQueue.matchChomp("invalid"));
        assertEquals("sample(data)for(testing)", tokenQueue.remainder());
    }

    @Test
    public void testMatchChompWithConsumeTo() {
        tokenQueue.consumeTo("(");
        assertTrue(tokenQueue.matchChomp("("));
        assertEquals("data)for(testing", tokenQueue.remainder());
    }

    @Test
    public void testMatchChompWithChompTo() {
        tokenQueue.chompTo("(");
        assertTrue(tokenQueue.matchChomp("("));
        assertEquals("data)for(testing", tokenQueue.remainder());
    }
}
