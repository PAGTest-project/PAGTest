
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_inTest {

    @Test
    public void testIn_StringFound() {
        String needle = "apple";
        String[] haystack = {"banana", "apple", "cherry"};
        assertTrue(StringUtil.in(needle, haystack));
    }

    @Test
    public void testIn_StringNotFound() {
        String needle = "grape";
        String[] haystack = {"banana", "apple", "cherry"};
        assertFalse(StringUtil.in(needle, haystack));
    }

    @Test
    public void testIn_EmptyHaystack() {
        String needle = "apple";
        String[] haystack = {};
        assertFalse(StringUtil.in(needle, haystack));
    }

    @Test
    public void testIn_NullNeedle() {
        String needle = null;
        String[] haystack = {"banana", "apple", "cherry"};
        assertFalse(StringUtil.in(needle, haystack));
    }

    @Test
    public void testIn_NullHaystack() {
        String needle = "apple";
        String[] haystack = null;
        assertFalse(StringUtil.in(needle, haystack));
    }
}
