
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_unescapeTest {

    @BeforeEach
    public void setUp() {
        // No setup required for static method
    }

    @Test
    public void testUnescapeWithNoEscapes() {
        String input = "noEscapesHere";
        String expected = "noEscapesHere";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithSingleEscape() {
        String input = "single\\Escape";
        String expected = "singleEscape";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithConsecutiveEscapes() {
        String input = "consecutive\\\\Escapes";
        String expected = "consecutive\\Escapes";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithMixedEscapes() {
        String input = "mixed\\Escapes\\Here";
        String expected = "mixedEscapesHere";
        assertEquals(expected, TokenQueue.unescape(input));
    }
}
