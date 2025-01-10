
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_joiningTest {

    @Test
    public void testJoiningWithDelimiter() {
        String delimiter = ", ";
        String result = Arrays.asList("apple", "banana", "cherry")
                .stream()
                .collect(StringUtil.joining(delimiter));
        assertEquals("apple, banana, cherry", result);
    }

    @Test
    public void testJoiningWithEmptyCollection() {
        String delimiter = ", ";
        String result = Collections.<String>emptyList()
                .stream()
                .collect(StringUtil.joining(delimiter));
        assertEquals("", result);
    }

    @Test
    public void testJoiningWithSingleElement() {
        String delimiter = ", ";
        String result = Collections.singletonList("apple")
                .stream()
                .collect(StringUtil.joining(delimiter));
        assertEquals("apple", result);
    }

    @Test
    public void testJoiningWithWhitespaceNormalization() {
        String delimiter = " ";
        String result = Arrays.asList("  apple", "banana  ", " cherry ")
                .stream()
                .collect(StringUtil.joining(delimiter));
        assertEquals("apple banana cherry", StringUtil.normaliseWhitespace(result));
    }

    @Test
    public void testJoiningWithAsciiCheck() {
        String delimiter = "-";
        String result = Arrays.asList("apple", "banana", "cherry")
                .stream()
                .collect(StringUtil.joining(delimiter));
        assertTrue(StringUtil.isAscii(result));
    }
}
