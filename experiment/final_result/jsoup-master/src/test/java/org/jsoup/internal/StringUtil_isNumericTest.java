
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isNumericTest {

    @Test
    public void testIsNumeric() {
        // Test for null input
        assertFalse(StringUtil.isNumeric(null));

        // Test for empty string
        assertFalse(StringUtil.isNumeric(""));

        // Test for non-numeric string
        assertFalse(StringUtil.isNumeric("abc123"));

        // Test for numeric string
        assertTrue(StringUtil.isNumeric("123456"));

        // Test for numeric string with leading/trailing spaces
        assertFalse(StringUtil.isNumeric(" 123456 "));
    }
}
