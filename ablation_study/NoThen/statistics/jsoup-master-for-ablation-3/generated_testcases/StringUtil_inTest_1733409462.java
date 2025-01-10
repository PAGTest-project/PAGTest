
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_inTest {

    @Test
    void testIn_StringFound() {
        assertTrue(StringUtil.in("apple", new String[]{"banana", "apple", "cherry"}));
    }

    @Test
    void testIn_StringNotFound() {
        assertFalse(StringUtil.in("grape", new String[]{"banana", "apple", "cherry"}));
    }

    @Test
    void testIn_EmptyHaystack() {
        assertFalse(StringUtil.in("apple", new String[]{}));
    }

    @Test
    void testIn_NullNeedle() {
        assertFalse(StringUtil.in(null, new String[]{"banana", "apple", "cherry"}));
    }

    @Test
    void testIn_EmptyNeedle() {
        assertTrue(StringUtil.in("", new String[]{"", "banana", "apple", "cherry"}));
    }

    @Test
    void testIn_CaseSensitive() {
        assertFalse(StringUtil.in("Apple", new String[]{"banana", "apple", "cherry"}));
    }
}
