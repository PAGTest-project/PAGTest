
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_startsWithNewlineTest {

    @Test
    public void testStartsWithNewline_NullString() {
        assertFalse(StringUtil.startsWithNewline(null));
    }

    @Test
    public void testStartsWithNewline_EmptyString() {
        assertFalse(StringUtil.startsWithNewline(""));
    }

    @Test
    public void testStartsWithNewline_NoNewline() {
        assertFalse(StringUtil.startsWithNewline("Hello"));
    }

    @Test
    public void testStartsWithNewline_WithNewline() {
        assertTrue(StringUtil.startsWithNewline("\nHello"));
    }

    @Test
    public void testStartsWithNewline_OnlyNewline() {
        assertTrue(StringUtil.startsWithNewline("\n"));
    }

    @Test
    public void testStartsWithNewline_WhitespaceBeforeNewline() {
        assertFalse(StringUtil.startsWithNewline(" \nHello"));
    }
}
