
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_normaliseWhitespaceTest {

    @Test
    void testNormaliseWhitespace() {
        assertEquals("Hello World", StringUtil.normaliseWhitespace("Hello  World"));
        assertEquals("Hello World", StringUtil.normaliseWhitespace("Hello\tWorld"));
        assertEquals("Hello World", StringUtil.normaliseWhitespace("Hello\nWorld"));
        assertEquals("Hello World", StringUtil.normaliseWhitespace("Hello\fWorld"));
        assertEquals("Hello World", StringUtil.normaliseWhitespace("Hello\rWorld"));
        assertEquals("Hello World", StringUtil.normaliseWhitespace("Hello \t\n\f\rWorld"));
        assertEquals("Hello World", StringUtil.normaliseWhitespace("  Hello  World  "));
        assertEquals("", StringUtil.normaliseWhitespace(""));
        assertEquals(" ", StringUtil.normaliseWhitespace(" "));
        assertEquals(" ", StringUtil.normaliseWhitespace("\t\n\f\r"));
    }
}
