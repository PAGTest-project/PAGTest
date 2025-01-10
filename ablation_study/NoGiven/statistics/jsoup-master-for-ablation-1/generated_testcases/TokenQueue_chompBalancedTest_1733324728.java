
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompBalancedTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("");
    }

    @Test
    public void testChompBalancedSimple() {
        tokenQueue = new TokenQueue("(one (two) three)");
        assertEquals("one (two) three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithEscapes() {
        tokenQueue = new TokenQueue("(one \\(two\\) three)");
        assertEquals("one \\(two\\) three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithQuotes() {
        tokenQueue = new TokenQueue("(one 'two' \"three\")");
        assertEquals("one 'two' \"three\"", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithRegexQE() {
        tokenQueue = new TokenQueue("(one \\Qtwo\\E three)");
        assertEquals("one \\Qtwo\\E three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedUnbalanced() {
        tokenQueue = new TokenQueue("(one (two) three");
        assertThrows(IllegalArgumentException.class, () -> tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedEmpty() {
        tokenQueue = new TokenQueue("");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithLeadingWhitespace() {
        tokenQueue = new TokenQueue("   (one (two) three)");
        tokenQueue.consumeWhitespace();
        assertEquals("one (two) three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithLeadingWord() {
        tokenQueue = new TokenQueue("start (one (two) three)");
        tokenQueue.consumeWord();
        assertEquals("one (two) three", tokenQueue.chompBalanced('(', ')'));
    }
}
