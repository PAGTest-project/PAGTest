
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_appendNormalisedWhitespaceTest {

    @Test
    public void testAppendNormalisedWhitespace() {
        StringBuilder accum = new StringBuilder();

        // Test case 1: Normal string with no leading whitespace
        StringUtil.appendNormalisedWhitespace(accum, "Hello World", false);
        assertEquals("Hello World", accum.toString());
        accum.setLength(0); // Reset StringBuilder

        // Test case 2: String with leading and multiple internal whitespaces
        StringUtil.appendNormalisedWhitespace(accum, "  Hello   World  ", false);
        assertEquals(" Hello World ", accum.toString());
        accum.setLength(0); // Reset StringBuilder

        // Test case 3: String with leading whitespace and stripLeading true
        StringUtil.appendNormalisedWhitespace(accum, "  Hello World", true);
        assertEquals("Hello World", accum.toString());
        accum.setLength(0); // Reset StringBuilder

        // Test case 4: String with only whitespace
        StringUtil.appendNormalisedWhitespace(accum, "     ", false);
        assertEquals(" ", accum.toString());
        accum.setLength(0); // Reset StringBuilder

        // Test case 5: String with invisible characters
        StringUtil.appendNormalisedWhitespace(accum, "Hello\u200BWorld", false);
        assertEquals("HelloWorld", accum.toString());
        accum.setLength(0); // Reset StringBuilder
    }
}
