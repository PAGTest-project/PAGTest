
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeWhitespaceTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("  \t\n  test  ");
    }

    @Test
    public void testConsumeWhitespace() {
        assertTrue(tokenQueue.consumeWhitespace());
        assertEquals("test  ", tokenQueue.remainder());
    }

    @Test
    public void testConsumeWhitespaceNoWhitespace() {
        tokenQueue = new TokenQueue("test");
        assertFalse(tokenQueue.consumeWhitespace());
        assertEquals("test", tokenQueue.remainder());
    }
}
