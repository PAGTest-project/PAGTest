
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
    public void testJoinSingleElementIterator() {
        Iterator<String> singleElementIterator = Collections.singletonList("single").iterator();
        assertEquals("single", StringUtil.join(singleElementIterator, ","));
    }

    @Test
    public void testJoinMultipleElementsIterator() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one,two,three", StringUtil.join(multipleElementsIterator, ","));
    }

    @Test
    public void testJoinWithNullSeparator() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("onetwothree", StringUtil.join(multipleElementsIterator, null));
    }

    @Test
    public void testJoinWithEmptySeparator() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("onetwothree", StringUtil.join(multipleElementsIterator, ""));
    }

    @Test
    public void testJoinWithWhitespaceSeparator() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one two three", StringUtil.join(multipleElementsIterator, " "));
    }

    @Test
    public void testJoinWithSpecialCharactersSeparator() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one|two|three", StringUtil.join(multipleElementsIterator, "|"));
    }
}
