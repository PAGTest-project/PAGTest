
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_startsWithNewlineTest {

    @Test
    public void testStartsWithNewline() {
        // Test case for a string that starts with a newline
        assertTrue(StringUtil.startsWithNewline("\nHello"));

        // Test case for a string that does not start with a newline
        assertFalse(StringUtil.startsWithNewline("Hello"));

        // Test case for an empty string
        assertFalse(StringUtil.startsWithNewline(""));

        // Test case for a null string
        assertFalse(StringUtil.startsWithNewline(null));
    }
}
