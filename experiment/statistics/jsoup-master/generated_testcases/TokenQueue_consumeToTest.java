
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("Hello World");
    }

    @Test
    public void testConsumeTo_SequenceFound() {
        String result = tokenQueue.consumeTo("World");
        assertEquals("Hello ", result);
        // Remove the assertion for getPos() as it is not available
    }

    @Test
    public void testConsumeTo_SequenceNotFound() {
        String result = tokenQueue.consumeTo("Universe");
        assertEquals("Hello World", result);
        // Remove the assertion for getPos() as it is not available
    }
}
