
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
    public void testIsBlankWithWhitespaceString() {
        assertTrue(StringUtil.isBlank("   "));
    }

    @Test
    public void testIsBlankWithNonWhitespaceString() {
        assertFalse(StringUtil.isBlank("Hello"));
    }

    @Test
    public void testIsBlankWithMixedString() {
        assertFalse(StringUtil.isBlank("  Hello  "));
    }

    @Test
    public void testIsBlankWithNewlineString() {
        assertTrue(StringUtil.isBlank("\n\t\r"));
    }

    @Test
    public void testIsBlankWithNormalizedWhitespaceString() {
        String normalizedWhitespace = StringUtil.normaliseWhitespace("  Hello  ");
        assertFalse(StringUtil.isBlank(normalizedWhitespace));
    }
}
