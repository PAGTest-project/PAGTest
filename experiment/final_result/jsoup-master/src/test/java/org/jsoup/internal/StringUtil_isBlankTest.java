
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isBlankTest {

    @Test
    public void testIsBlank_NullString() {
        assertTrue(StringUtil.isBlank(null));
    }

    @Test
    public void testIsBlank_EmptyString() {
        assertTrue(StringUtil.isBlank(""));
    }

    @Test
    public void testIsBlank_WhitespaceString() {
        assertTrue(StringUtil.isBlank("   \t\n\r "));
    }

    @Test
    public void testIsBlank_NonWhitespaceString() {
        assertFalse(StringUtil.isBlank("  a  "));
    }
}
