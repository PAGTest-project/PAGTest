
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_normaliseWhitespaceTest {

    @Test
    public void testNormaliseWhitespace() {
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello   world"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\tworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\nworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\rworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\fworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello   \t\n\r\fworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("   hello   world   "));
        assertEquals("", StringUtil.normaliseWhitespace(""));
        assertEquals("", StringUtil.normaliseWhitespace("   \t\n\r\f"));
    }
}
