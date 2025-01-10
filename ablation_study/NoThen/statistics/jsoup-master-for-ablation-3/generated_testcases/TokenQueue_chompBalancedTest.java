
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompBalancedTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("One (Two) Three");
    }

    @Test
    public void testChompBalancedSimple() {
        tokenQueue.consumeTo("(");
        assertEquals("Two", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedNested() {
        tokenQueue = new TokenQueue("One (Two (Nested) Three) Four");
        tokenQueue.consumeTo("(");
        assertEquals("Two (Nested) Three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedUnmatchedOpen() {
        tokenQueue = new TokenQueue("One (Two (Nested) Three Four");
        tokenQueue.consumeTo("(");
        assertThrows(IllegalArgumentException.class, () -> tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedUnmatchedClose() {
        tokenQueue = new TokenQueue("One (Two (Nested) Three) Four) Five");
        tokenQueue.consumeTo("(");
        assertEquals("Two (Nested) Three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithQuotes() {
        tokenQueue = new TokenQueue("One (Two '(\"Nested\")' Three) Four");
        tokenQueue.consumeTo("(");
        assertEquals("Two '(\"Nested\")' Three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithEscapes() {
        tokenQueue = new TokenQueue("One (Two \\(Nested\\) Three) Four");
        tokenQueue.consumeTo("(");
        assertEquals("Two \\(Nested\\) Three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedEmpty() {
        tokenQueue = new TokenQueue("One () Three");
        tokenQueue.consumeTo("(");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedNoMatch() {
        tokenQueue = new TokenQueue("One Two Three");
        tokenQueue.consumeTo("(");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }
}
