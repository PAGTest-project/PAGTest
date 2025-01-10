
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isAsciiTest {

    @Test
    public void testIsAscii_AllAsciiCharacters() {
        assertTrue(StringUtil.isAscii("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
    }

    @Test
    public void testIsAscii_NonAsciiCharacter() {
        assertFalse(StringUtil.isAscii("é"));
    }

    @Test
    public void testIsAscii_EmptyString() {
        assertTrue(StringUtil.isAscii(""));
    }

    @Test
    public void testIsAscii_NullString() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.isAscii(null));
    }
}
