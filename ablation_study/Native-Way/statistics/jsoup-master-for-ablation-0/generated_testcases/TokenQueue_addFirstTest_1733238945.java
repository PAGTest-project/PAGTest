
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_addFirstTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("initial data");
    }

    @Test
    public void testAddFirstWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        tokenQueue.addFirst("new data");
        assertEquals("new data", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithNonEmptyQueue() {
        tokenQueue.addFirst("new data");
        assertEquals("new datainitial data", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithPositionedQueue() {
        tokenQueue.consumeTo("data");
        tokenQueue.addFirst("new ");
        assertEquals("new datainitial data", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithEscapedData() {
        tokenQueue = new TokenQueue("\\initial data");
        tokenQueue.addFirst("new ");
        assertEquals("new \\initial data", tokenQueue.toString());
    }

    @Test
    public void testAddFirstWithWhitespace() {
        tokenQueue = new TokenQueue(" initial data");
        tokenQueue.addFirst("new");
        assertEquals("new initial data", tokenQueue.toString());
    }
}
