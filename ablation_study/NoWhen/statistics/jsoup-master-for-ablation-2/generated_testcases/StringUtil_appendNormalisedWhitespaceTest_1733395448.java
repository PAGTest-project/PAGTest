
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_appendNormalisedWhitespaceTest {

    @Test
    public void testAppendNormalisedWhitespace_NoStripLeading() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "  Hello  World  ", false);
        assertEquals(" Hello World ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_StripLeading() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "  Hello  World  ", true);
        assertEquals("Hello World ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_EmptyString() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "", false);
        assertEquals("", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_OnlyWhitespace() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "     ", false);
        assertEquals(" ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_InvisibleChars() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "Hello\u200BWorld", false);
        assertEquals("HelloWorld", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_MixedWhitespaceAndInvisibleChars() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "  Hello\u200B  World\u200B  ", false);
        assertEquals(" Hello World ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_NewlineAndTab() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "Hello\n\tWorld", false);
        assertEquals("Hello World", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_MultipleSpaces() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "Hello      World", false);
        assertEquals("Hello World", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_LeadingWhitespaceStrip() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "   Hello World", true);
        assertEquals("Hello World", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespace_TrailingWhitespaceNoStrip() {
        StringBuilder accum = new StringBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "Hello World   ", false);
        assertEquals("Hello World ", accum.toString());
    }
}
