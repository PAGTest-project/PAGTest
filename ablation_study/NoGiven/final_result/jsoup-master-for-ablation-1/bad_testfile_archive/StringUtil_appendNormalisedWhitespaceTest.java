
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_appendNormalisedWhitespaceTest {

    @Test
    public void testAppendNormalisedWhitespace_NoStripLeading() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "   hello   \r \n  there    \n", false);
        assertEquals(" hello there ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_StripLeading() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "   hello   \r \n  there    \n", true);
        assertEquals("hello there ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_NoWhitespace() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "hello", false);
        assertEquals("hello", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_OnlyWhitespace() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "    \r \n \r\n", false);
        assertEquals(" ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_InvisibleChars() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "hello\u200Bthere", false);
        assertEquals("hellothere", accum.toString());
    }
}
