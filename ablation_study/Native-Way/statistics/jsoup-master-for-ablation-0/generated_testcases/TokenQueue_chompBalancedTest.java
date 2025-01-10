
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompBalancedTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("(one (two) three) four");
    }

    @Test
    public void testChompBalancedSimple() {
        tokenQueue = new TokenQueue("(one (two) three) four");
        assertEquals("one (two) three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedEmpty() {
        tokenQueue = new TokenQueue("");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedUnmatchedOpen() {
        tokenQueue = new TokenQueue("(one (two) three");
        assertThrows(IllegalArgumentException.class, () -> tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedUnmatchedClose() {
        tokenQueue = new TokenQueue("one) two (three)");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedNestedQuotes() {
        tokenQueue = new TokenQueue("(one '(\"two\")' three) four");
        assertEquals("one '(\"two\")' three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedRegexQE() {
        tokenQueue = new TokenQueue("(one \\Q(two)\\E three) four");
        assertEquals("one \\Q(two)\\E three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedMixedQuotesAndRegexQE() {
        tokenQueue = new TokenQueue("(one '(\\Qtwo\\E)' three) four");
        assertEquals("one '(\\Qtwo\\E)' three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithEscapes() {
        tokenQueue = new TokenQueue("(one \\(two\\) three) four");
        assertEquals("one \\(two\\) three", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithEmptyBalanced() {
        tokenQueue = new TokenQueue("() four");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithMultipleBalanced() {
        tokenQueue = new TokenQueue("(one) (two) (three) four");
        assertEquals("one", tokenQueue.chompBalanced('(', ')'));
    }
}
