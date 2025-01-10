
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
    public void testChompBalancedNested() {
        tokenQueue = new TokenQueue("(one (two (three) four) five)");
        assertEquals("one (two (three) four) five", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedUnbalanced() {
        tokenQueue = new TokenQueue("(one (two (three) four) five");
        assertThrows(IllegalArgumentException.class, () -> tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithQuotes() {
        tokenQueue = new TokenQueue("(one 'two (three)' four)");
        assertEquals("one 'two (three)' four", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedWithEscapes() {
        tokenQueue = new TokenQueue("(one \\(two) three)");
        assertEquals("one \\(two", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedEmpty() {
        tokenQueue = new TokenQueue("");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }

    @Test
    public void testChompBalancedNoMatch() {
        tokenQueue = new TokenQueue("one two three");
        assertEquals("", tokenQueue.chompBalanced('(', ')'));
    }
}
