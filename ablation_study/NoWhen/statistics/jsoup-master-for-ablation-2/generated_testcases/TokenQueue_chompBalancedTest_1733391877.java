
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
    public void testChompBalanced_Simple() {
        String result = tokenQueue.chompBalanced('(', ')');
        assertEquals("one (two) three", result);
    }

    @Test
    public void testChompBalanced_Empty() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.chompBalanced('(', ')');
        assertEquals("", result);
    }

    @Test
    public void testChompBalanced_Unbalanced() {
        tokenQueue = new TokenQueue("(one (two) three");
        assertThrows(IllegalArgumentException.class, () -> {
            tokenQueue.chompBalanced('(', ')');
        });
    }

    @Test
    public void testChompBalanced_WithQuotes() {
        tokenQueue = new TokenQueue("(one '(\"two\")' three) four");
        String result = tokenQueue.chompBalanced('(', ')');
        assertEquals("one '(\"two\")' three", result);
    }

    @Test
    public void testChompBalanced_WithEscapes() {
        tokenQueue = new TokenQueue("(one \\(two\\) three) four");
        String result = tokenQueue.chompBalanced('(', ')');
        assertEquals("one \\(two\\) three", result);
    }

    @Test
    public void testChompBalanced_WithRegexQE() {
        tokenQueue = new TokenQueue("(one \\Q(two)\\E three) four");
        String result = tokenQueue.chompBalanced('(', ')');
        assertEquals("one \\Q(two)\\E three", result);
    }
}
