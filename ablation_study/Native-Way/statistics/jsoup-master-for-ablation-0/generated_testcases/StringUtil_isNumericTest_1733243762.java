
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isNumericTest {

    @Test
    void testIsNumeric() {
        assertTrue(StringUtil.isNumeric("1234567890"));
        assertFalse(StringUtil.isNumeric("12345a67890"));
        assertFalse(StringUtil.isNumeric(""));
        assertFalse(StringUtil.isNumeric(null));
        assertFalse(StringUtil.isNumeric(" "));
        assertFalse(StringUtil.isNumeric("123 456"));
        assertFalse(StringUtil.isNumeric("123.456"));
        assertFalse(StringUtil.isNumeric("123,456"));
    }
}
