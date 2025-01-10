
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_joinTest {

    @Test
    public void testJoinEmptyIterator() {
        Iterator<String> emptyIterator = Collections.<String>emptyList().iterator();
        assertEquals("", StringUtil.join(emptyIterator, ","));
    }

    @Test
    public void testJoinSingleElement() {
        Iterator<String> singleElementIterator = Arrays.asList("one").iterator();
        assertEquals("one", StringUtil.join(singleElementIterator, ","));
    }

    @Test
    public void testJoinMultipleElements() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one,two,three", StringUtil.join(multipleElementsIterator, ","));
    }

    @Test
    public void testJoinWithWhitespaceSeparator() {
        Iterator<String> elementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one two three", StringUtil.join(elementsIterator, " "));
    }

    @Test
    public void testJoinWithEmptySeparator() {
        Iterator<String> elementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("onetwothree", StringUtil.join(elementsIterator, ""));
    }

    @Test
    public void testJoinWithNullSeparator() {
        Iterator<String> elementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("onetwothree", StringUtil.join(elementsIterator, null));
    }

    @Test
    public void testJoinWithNullElements() {
        Iterator<String> elementsIterator = Arrays.asList("one", null, "three").iterator();
        assertEquals("one,null,three", StringUtil.join(elementsIterator, ","));
    }
}
