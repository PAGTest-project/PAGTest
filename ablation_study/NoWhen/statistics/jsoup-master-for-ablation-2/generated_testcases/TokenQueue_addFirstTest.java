
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_addFirstTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("initialQueue");
    }

    @Test
    public void testAddFirst() {
        tokenQueue.addFirst("prepended");
        assertEquals("prependedinitialQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstEmptyQueue() {
        tokenQueue = new TokenQueue("");
        tokenQueue.addFirst("prepended");
        assertEquals("prepended", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithConsume() {
        tokenQueue.consume("initial");
        tokenQueue.addFirst("prepended");
        assertEquals("prependedQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithConsumeTo() {
        tokenQueue.consumeTo("Queue");
        tokenQueue.addFirst("prepended");
        assertEquals("prependedQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithMatches() {
        tokenQueue.addFirst("prepended");
        assertTrue(tokenQueue.matches("prependedinitialQueue"));
    }

    @Test
    public void testAddFirstWithExceptionHandling() {
        tokenQueue.consume("initial");
        tokenQueue.addFirst("prepended");
        try {
            tokenQueue.consume("invalid");
            fail("should have thrown IllegalStateException");
        } catch (IllegalStateException expected) {
            assertEquals("Queue did not match expected sequence", expected.getMessage());
        }
    }
}
