
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isBlankTest {

    @Test
    public void testIsBlankWithNullString() {
        assertTrue(StringUtil.isBlank(null));
    }

    @Test
    public void testIsBlankWithEmptyString() {
        assertTrue(StringUtil.isBlank(""));
    }

    @Test
    public void testIsBlankWithWhitespaceOnlyString() {
        assertTrue(StringUtil.isBlank("   \t\n\r"));
    }

    @Test
    public void testIsBlankWithNonWhitespaceString() {
        assertFalse(StringUtil.isBlank("hello"));
    }

    @Test
    public void testIsBlankWithMixedString() {
        assertFalse(StringUtil.isBlank("  hello  "));
    }
}
