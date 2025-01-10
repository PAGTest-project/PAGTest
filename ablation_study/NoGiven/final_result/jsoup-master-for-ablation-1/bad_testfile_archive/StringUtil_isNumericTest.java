
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isNumericTest {

    @Test
    void testIsNumeric() {
        // Test cases for null and empty strings
        assertFalse(StringUtil.isNumeric(null));
        assertFalse(StringUtil.isNumeric(""));

        // Test cases for numeric strings
        assertTrue(StringUtil.isNumeric("1234567890"));
        assertTrue(StringUtil.isNumeric("0"));
        assertTrue(StringUtil.isNumeric("9876543210"));

        // Test cases for non-numeric strings
        assertFalse(StringUtil.isNumeric("123abc"));
        assertFalse(StringUtil.isNumeric("abc123"));
        assertFalse(StringUtil.isNumeric("abc"));
        assertFalse(StringUtil.isNumeric("123.45"));
        assertFalse(StringUtil.isNumeric("  123"));
        assertFalse(StringUtil.isNumeric("123  "));
        assertFalse(StringUtil.isNumeric("123\n"));
        assertFalse(StringUtil.isNumeric("\t123"));
    }
}
