
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_normaliseWhitespaceTest {

    @Test
    public void testNormaliseWhitespace() {
        // Test case for a string with multiple spaces and newlines
        assertEquals("hello world", StringUtil.normaliseWhitespace("  hello  \n  world  "));

        // Test case for a string with only whitespace
        assertEquals("", StringUtil.normaliseWhitespace("   \r\n  "));

        // Test case for a string with no whitespace
        assertEquals("helloworld", StringUtil.normaliseWhitespace("helloworld"));

        // Test case for a string with leading and trailing whitespace
        assertEquals("hello world", StringUtil.normaliseWhitespace("   hello world   "));

        // Test case for a string with mixed whitespace characters
        assertEquals("hello world", StringUtil.normaliseWhitespace("hello\tworld\n"));
    }
}
