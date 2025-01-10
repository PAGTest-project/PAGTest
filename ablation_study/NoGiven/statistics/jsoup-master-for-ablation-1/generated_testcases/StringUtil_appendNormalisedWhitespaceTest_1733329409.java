
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_appendNormalisedWhitespaceTest {

    @Test
    public void testAppendNormalisedWhitespace_NoStripLeading() {
        StringBuilder accum = StringUtil.borrowBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "   hello   \r \n  there    \n", false);
        assertEquals(" hello there ", StringUtil.releaseBuilder(accum));
    }

    @Test
    public void testAppendNormalisedWhitespace_StripLeading() {
        StringBuilder accum = StringUtil.borrowBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "   hello   \r \n  there    \n", true);
        assertEquals("hello there ", StringUtil.releaseBuilder(accum));
    }

    @Test
    public void testAppendNormalisedWhitespace_NoWhitespace() {
        StringBuilder accum = StringUtil.borrowBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "hello", false);
        assertEquals("hello", StringUtil.releaseBuilder(accum));
    }

    @Test
    public void testAppendNormalisedWhitespace_OnlyWhitespace() {
        StringBuilder accum = StringUtil.borrowBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "    \r \n \r\n", false);
        assertEquals(" ", StringUtil.releaseBuilder(accum));
    }

    @Test
    public void testAppendNormalisedWhitespace_InvisibleChars() {
        StringBuilder accum = StringUtil.borrowBuilder();
        StringUtil.appendNormalisedWhitespace(accum, "hello\u200Bthere", false);
        assertEquals("hellothere", StringUtil.releaseBuilder(accum));
    }
}
