
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_appendNormalisedWhitespaceTest {

    private StringBuilder accum;

    @BeforeEach
    public void setUp() {
        accum = StringUtil.borrowBuilder();
    }

    @Test
    public void testAppendNormalisedWhitespaceWithLeadingWhitespace() {
        String input = "  Hello World";
        StringUtil.appendNormalisedWhitespace(accum, input, true);
        assertEquals("Hello World", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithoutLeadingWhitespace() {
        String input = "  Hello World";
        StringUtil.appendNormalisedWhitespace(accum, input, false);
        assertEquals(" Hello World", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithMultipleSpaces() {
        String input = "Hello   World";
        StringUtil.appendNormalisedWhitespace(accum, input, false);
        assertEquals("Hello World", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithNewlineAndTab() {
        String input = "Hello\n\tWorld";
        StringUtil.appendNormalisedWhitespace(accum, input, false);
        assertEquals("Hello World", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithInvisibleChars() {
        String input = "Hello\u200BWorld";
        StringUtil.appendNormalisedWhitespace(accum, input, false);
        assertEquals("HelloWorld", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithEmptyString() {
        String input = "";
        StringUtil.appendNormalisedWhitespace(accum, input, false);
        assertEquals("", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithOnlyWhitespace() {
        String input = "   ";
        StringUtil.appendNormalisedWhitespace(accum, input, false);
        assertEquals(" ", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithOnlyWhitespaceAndStripLeading() {
        String input = "   ";
        StringUtil.appendNormalisedWhitespace(accum, input, true);
        assertEquals("", accum.toString());
    }

    @Test
    public void testAppendNormalisedWhitespaceWithHighSurrogate() {
        String input = "\ud869\udeb2\u304b\u309a  1";
        StringUtil.appendNormalisedWhitespace(accum, input, false);
        assertEquals("\ud869\udeb2\u304b\u309a 1", accum.toString());
    }
}
