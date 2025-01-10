
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
    public void testAddFirstWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeq", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithNonEmptyQueue() {
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeqinitialQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithWhitespaceConsumed() {
        tokenQueue = new TokenQueue("   initialQueue");
        tokenQueue.consumeWhitespace();
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeqinitialQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstAndMatch() {
        tokenQueue.addFirst("newSeq");
        assertTrue(tokenQueue.matches("newSeq"));
    }

    @Test
    public void testAddFirstAndConsumeTo() {
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeq", tokenQueue.consumeTo("initialQueue"));
    }
}
