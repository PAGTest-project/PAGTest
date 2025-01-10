
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_startsWithNewlineTest {

    @Test
    public void testStartsWithNewline() {
        assertTrue(StringUtil.startsWithNewline("\nHello"));
        assertFalse(StringUtil.startsWithNewline("Hello"));
        assertFalse(StringUtil.startsWithNewline(""));
        assertFalse(StringUtil.startsWithNewline(null));
    }

    @Test
    public void testStartsWithNewlineWithWhitespace() {
        assertFalse(StringUtil.startsWithNewline(" \nHello"));
        assertFalse(StringUtil.startsWithNewline("\tHello"));
        assertFalse(StringUtil.startsWithNewline("\r\nHello"));
    }

    @Test
    public void testStartsWithNewlineWithNormalizedWhitespace() {
        String normalized = StringUtil.normaliseWhitespace(" \nHello");
        assertTrue(StringUtil.startsWithNewline(normalized));
    }
}
