
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_normaliseWhitespaceTest {

    @Test
    public void testNormaliseWhitespace() {
        // Test case for normalising whitespace
        assertEquals("hello world", StringUtil.normaliseWhitespace("  hello  world  "));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\tworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\nworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\rworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\fworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello world"));
        assertEquals("", StringUtil.normaliseWhitespace(""));
        assertEquals("", StringUtil.normaliseWhitespace("   "));
        assertEquals("", StringUtil.normaliseWhitespace("\t\n\r\f"));
    }

    @Test
    public void testNormaliseWhitespaceWithLeadingWhitespace() {
        // Test case for normalising whitespace with leading whitespace
        assertEquals("hello world", StringUtil.normaliseWhitespace("  hello  world  "));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\thello\tworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\nhello\nworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\rhello\rworld"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\fhello\fworld"));
    }

    @Test
    public void testNormaliseWhitespaceWithTrailingWhitespace() {
        // Test case for normalising whitespace with trailing whitespace
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello  world  "));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\tworld\t"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\nworld\n"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\rworld\r"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\fworld\f"));
    }

    @Test
    public void testNormaliseWhitespaceWithMixedWhitespace() {
        // Test case for normalising whitespace with mixed whitespace
        assertEquals("hello world", StringUtil.normaliseWhitespace("  hello\tworld\n"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\thello\nworld\r"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\nhello\rworld\f"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\rhello\fworld\t"));
        assertEquals("hello world", StringUtil.normaliseWhitespace("\fhello\tworld\n"));
    }

    @Test
    public void testNormaliseWhitespaceWithNoWhitespace() {
        // Test case for normalising whitespace with no whitespace
        assertEquals("helloworld", StringUtil.normaliseWhitespace("helloworld"));
    }
}
