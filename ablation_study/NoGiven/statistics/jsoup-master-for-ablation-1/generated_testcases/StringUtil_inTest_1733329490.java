
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_inTest {

    @Test
    void testIn_StringFound() {
        assertTrue(StringUtil.in("apple", "banana", "apple", "cherry"));
    }

    @Test
    void testIn_StringNotFound() {
        assertFalse(StringUtil.in("grape", "banana", "apple", "cherry"));
    }

    @Test
    void testIn_EmptyHaystack() {
        assertFalse(StringUtil.in("apple"));
    }

    @Test
    void testIn_NullNeedle() {
        assertFalse(StringUtil.in(null, "banana", "apple", "cherry"));
    }

    @Test
    void testIn_EmptyNeedle() {
        assertTrue(StringUtil.in("", "", "banana", "apple", "cherry"));
    }

    @Test
    void testIn_MultipleOccurrences() {
        assertTrue(StringUtil.in("apple", "banana", "apple", "cherry", "apple"));
    }
}
