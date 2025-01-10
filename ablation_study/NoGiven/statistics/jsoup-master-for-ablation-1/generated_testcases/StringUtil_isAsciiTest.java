
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isAsciiTest {

    @Test
    public void testIsAsciiWithNullString() {
        assertThrows(NullPointerException.class, () -> StringUtil.isAscii(null));
    }

    @Test
    public void testIsAsciiWithEmptyString() {
        assertTrue(StringUtil.isAscii(""));
    }

    @Test
    public void testIsAsciiWithAsciiString() {
        assertTrue(StringUtil.isAscii("Hello, World!"));
    }

    @Test
    public void testIsAsciiWithNonAsciiString() {
        assertFalse(StringUtil.isAscii("Hello, 世界!"));
    }

    @Test
    public void testIsAsciiWithMixedString() {
        assertFalse(StringUtil.isAscii("Hello, 世界! This is ASCII."));
    }
}
