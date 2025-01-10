
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_unescapeTest {

    private static final char ESC = '\\';

    public static String unescape(String in) {
        StringBuilder out = new StringBuilder();
        char last = 0;
        for (char c : in.toCharArray()) {
            if (c == ESC) {
                if (last == ESC) {
                    out.append(c);
                    last = 0; // Reset last to avoid double escape
                }
            } else {
                out.append(c);
            }
            last = c;
        }
        return out.toString();
    }

    @Test
    public void testUnescapeWithNoEscapes() {
        String input = "noEscapesHere";
        assertEquals(input, unescape(input));
    }

    @Test
    public void testUnescapeWithSingleEscape() {
        String input = "single\\escape";
        assertEquals("singleescape", unescape(input));
    }

    @Test
    public void testUnescapeWithMultipleEscapes() {
        String input = "multi\\ple\\escapes";
        assertEquals("multiplescapes", unescape(input));
    }

    @Test
    public void testUnescapeWithConsecutiveEscapes() {
        String input = "consecutive\\\\escapes";
        assertEquals("consecutive\\escapes", unescape(input));
    }

    @Test
    public void testUnescapeWithEscapedEscape() {
        String input = "escaped\\escape\\";
        assertEquals("escapedescape", unescape(input));
    }

    @Test
    public void testUnescapeWithEmptyString() {
        String input = "";
        assertEquals(input, unescape(input));
    }

    @Test
    public void testUnescapeWithOnlyEscapes() {
        String input = "\\\\\\\\";
        assertEquals("\\\\", unescape(input));
    }

    @Test
    public void testUnescapeWithMixedCharacters() {
        String input = "a\\b\\c\\d\\e\\f";
        assertEquals("abcdef", unescape(input));
    }

    @Test
    public void testUnescapeWithSpecialCharacters() {
        String input = "special\\#\\.\\/\\\\characters";
        assertEquals("special#./\\characters", unescape(input));
    }

    @Test
    public void testUnescapeWithWhitespace() {
        String input = "whitespace \\ \\ \\ ";
        assertEquals("whitespace    ", unescape(input));
    }
}
