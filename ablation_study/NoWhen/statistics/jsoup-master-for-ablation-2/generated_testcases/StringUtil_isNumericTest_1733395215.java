
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isNumericTest {

    @Test
    public void testIsNumeric() {
        assertTrue(StringUtil.isNumeric("1234567890"));
        assertFalse(StringUtil.isNumeric(null));
        assertFalse(StringUtil.isNumeric(""));
        assertFalse(StringUtil.isNumeric("1234a56789"));
        assertFalse(StringUtil.isNumeric("  1234567890  "));
        assertFalse(StringUtil.isNumeric("1234567890 "));
        assertFalse(StringUtil.isNumeric(" 1234567890"));
        assertFalse(StringUtil.isNumeric("1234567890\t"));
        assertFalse(StringUtil.isNumeric("\t1234567890"));
        assertFalse(StringUtil.isNumeric("1234567890\n"));
        assertFalse(StringUtil.isNumeric("\n1234567890"));
    }
}
