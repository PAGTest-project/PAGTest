
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
    public void testAddFirstWithConsumedQueue() {
        tokenQueue.consumeTo("Queue");
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeqQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithConsumeToIgnoreCase() {
        tokenQueue.consumeToIgnoreCase("queue");
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeqQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithConsumeWhitespace() {
        tokenQueue = new TokenQueue("   initialQueue");
        tokenQueue.consumeWhitespace();
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeqinitialQueue", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithConsumeWord() {
        tokenQueue = new TokenQueue("word initialQueue");
        tokenQueue.consumeWord();
        tokenQueue.addFirst("newSeq");
        assertEquals("newSeq initialQueue", tokenQueue.toString());
    }
}
