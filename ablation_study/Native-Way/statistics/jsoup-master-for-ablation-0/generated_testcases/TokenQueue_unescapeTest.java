
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
        String input = "no escapes here";
        String expected = "no escapes here";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithSingleEscape() {
        String input = "single\\escape";
        String expected = "singleescape";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithMultipleEscapes() {
        String input = "multi\\ple\\escapes";
        String expected = "multiplescapes";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithConsecutiveEscapes() {
        String input = "consecutive\\\\escapes";
        String expected = "consecutive\\escapes";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithEscapeAtEnd() {
        String input = "escape at end\\";
        String expected = "escape at end";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithOnlyEscapes() {
        String input = "\\\\\\\\";
        String expected = "\\\\";
        assertEquals(expected, TokenQueue.unescape(input));
    }
}
