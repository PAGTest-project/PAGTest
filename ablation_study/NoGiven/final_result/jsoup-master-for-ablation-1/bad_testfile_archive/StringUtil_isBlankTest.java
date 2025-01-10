
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
    public void testIsBlankWithWhitespaceOnly() {
        assertTrue(StringUtil.isBlank("   \t\n\r"));
    }

    @Test
    public void testIsBlankWithNonWhitespaceCharacters() {
        assertFalse(StringUtil.isBlank("  abc  "));
    }

    @Test
    public void testIsBlankWithSingleNonWhitespaceCharacter() {
        assertFalse(StringUtil.isBlank("a"));
    }

    @Test
    public void testIsBlankWithNormalizedWhitespace() {
        String normalized = StringUtil.normaliseWhitespace("   \t\n\r");
        assertTrue(StringUtil.isBlank(normalized));
    }

    @Test
    public void testIsBlankWithNonNormalizedWhitespace() {
        String nonNormalized = "   \t\n\r";
        assertTrue(StringUtil.isBlank(nonNormalized));
    }

    @Test
    public void testIsBlankWithMixedCharacters() {
        assertFalse(StringUtil.isBlank("  a \t\n\r b "));
    }
}
