
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_toStringTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("initial data");
    }

    @Test
    public void testToStringWithInitialData() {
        assertEquals("initial data", tokenQueue.toString());
    }

    @Test
    public void testToStringAfterConsumingPartOfQueue() {
        tokenQueue.consumeTo("data");
        assertEquals("data", tokenQueue.toString());
    }

    @Test
    public void testToStringAfterConsumingAll() {
        tokenQueue.consumeTo("initial data");
        assertEquals("", tokenQueue.toString());
    }
}
