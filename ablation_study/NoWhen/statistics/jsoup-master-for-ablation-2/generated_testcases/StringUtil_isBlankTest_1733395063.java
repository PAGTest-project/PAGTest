
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isBlankTest {

    @Test
    public void testIsBlank() {
        // Test cases for null and empty strings
        assertTrue(StringUtil.isBlank(null));
        assertTrue(StringUtil.isBlank(""));

        // Test cases for strings with only whitespace characters
        assertTrue(StringUtil.isBlank(" "));
        assertTrue(StringUtil.isBlank("\t"));
        assertTrue(StringUtil.isBlank("\n"));
        assertTrue(StringUtil.isBlank("\r"));
        assertTrue(StringUtil.isBlank("\f"));
        assertTrue(StringUtil.isBlank(" \t\n\r\f"));

        // Test cases for strings with non-whitespace characters
        assertFalse(StringUtil.isBlank("a"));
        assertFalse(StringUtil.isBlank(" a "));
        assertFalse(StringUtil.isBlank("abc"));
        assertFalse(StringUtil.isBlank("123"));
        assertFalse(StringUtil.isBlank("a1b2c3"));
    }
}
