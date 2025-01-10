
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_joinTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testJoinEmptyIterator() {
        Iterator<String> emptyIterator = Collections.<String>emptyList().iterator();
        assertEquals("", StringUtil.join(emptyIterator, ","));
    }

    @Test
    public void testJoinSingleElement() {
        Iterator<String> singleElementIterator = Collections.singletonList("single").iterator();
        assertEquals("single", StringUtil.join(singleElementIterator, ","));
    }

    @Test
    public void testJoinMultipleElements() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one,two,three", StringUtil.join(multipleElementsIterator, ","));
    }

    @Test
    public void testJoinWithBlankStrings() {
        Iterator<String> blankStringsIterator = Arrays.asList("", "two", "").iterator();
        assertEquals(",two,", StringUtil.join(blankStringsIterator, ","));
    }

    @Test
    public void testJoinWithNumericStrings() {
        Iterator<String> numericStringsIterator = Arrays.asList("123", "456", "789").iterator();
        assertEquals("123,456,789", StringUtil.join(numericStringsIterator, ","));
    }

    @Test
    public void testJoinWithWhitespaceNormalization() {
        Iterator<String> whitespaceStringsIterator = Arrays.asList("  one  ", "  two  ", "  three  ").iterator();
        String joined = StringUtil.join(whitespaceStringsIterator, ",");
        assertEquals("  one  ,  two  ,  three  ", joined);
        assertEquals("one,two,three", StringUtil.normaliseWhitespace(joined));
    }

    @Test
    public void testJoinWithAsciiOnly() {
        Iterator<String> asciiStringsIterator = Arrays.asList("abc", "def", "ghi").iterator();
        String joined = StringUtil.join(asciiStringsIterator, ",");
        assertTrue(StringUtil.isAscii(joined));
    }
}
