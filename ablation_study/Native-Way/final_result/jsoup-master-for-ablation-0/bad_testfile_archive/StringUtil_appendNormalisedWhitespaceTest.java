
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_appendNormalisedWhitespaceTest {

    private StringBuilder accum;

    @BeforeEach
    public void setUp() {
        accum = new StringBuilder();
    }

    @Test
    public void testAppendNormalisedWhitespace_NoStripLeading() {
        StringUtil.appendNormalisedWhitespace(accum, "    \r \n \r\n", false);
        assertEquals(" ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_StripLeading() {
        StringUtil.appendNormalisedWhitespace(accum, "    \r \n \r\n", true);
        assertEquals("", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_MixedWhitespace() {
        StringUtil.appendNormalisedWhitespace(accum, "   hello   \r \n  there    \n", false);
        assertEquals(" hello there ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_NoWhitespace() {
        StringUtil.appendNormalisedWhitespace(accum, "hello", false);
        assertEquals("hello", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_NewlineOnly() {
        StringUtil.appendNormalisedWhitespace(accum, "hello\nthere", false);
        assertEquals("hello there", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_InvisibleChars() {
        StringUtil.appendNormalisedWhitespace(accum, "hello\u200Bthere", false);
        assertEquals("hellothere", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_LeadingWhitespaceStrip() {
        StringUtil.appendNormalisedWhitespace(accum, "   hello there", true);
        assertEquals("hello there", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_TrailingWhitespace() {
        StringUtil.appendNormalisedWhitespace(accum, "hello there   ", false);
        assertEquals("hello there ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_MultipleSpaces() {
        StringUtil.appendNormalisedWhitespace(accum, "hello     there", false);
        assertEquals("hello there", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_EmptyString() {
        StringUtil.appendNormalisedWhitespace(accum, "", false);
        assertEquals("", accum.toString());
    }
}
