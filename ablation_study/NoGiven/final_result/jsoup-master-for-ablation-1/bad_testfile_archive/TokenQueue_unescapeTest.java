
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_unescapeTest {

    @Test
    public void testUnescapeWithNoEscapes() {
        String input = "noEscapesHere";
        assertEquals(input, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithSingleEscape() {
        String input = "single\\escape";
        assertEquals("singleescape", TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithMultipleEscapes() {
        String input = "multi\\ple\\escapes";
        assertEquals("multiplescapes", TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithConsecutiveEscapes() {
        String input = "consecutive\\\\escapes";
        assertEquals("consecutive\\escapes", TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithEscapedEscape() {
        String input = "escaped\\escape\\";
        assertEquals("escapedescape", TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithEmptyString() {
        String input = "";
        assertEquals(input, TokenQueue.unescape(input));
    }

    @Test
    public void testUnescapeWithOnlyEscapes() {
        String input = "\\\\\\\\";
        assertEquals("\\\\", TokenQueue.unescape(input));
    }
}
