
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
        Iterator<String> singleElementIterator = Collections.singletonList("single").iterator();
        assertEquals("single", StringUtil.join(singleElementIterator, ","));
    }

    @Test
    public void testJoinMultipleElements() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one,two,three", StringUtil.join(multipleElementsIterator, ","));
    }

    @Test
    public void testJoinWithNullSeparator() {
        Iterator<String> elementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("onetwothree", StringUtil.join(elementsIterator, null));
    }

    @Test
    public void testJoinWithEmptySeparator() {
        Iterator<String> elementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("onetwothree", StringUtil.join(elementsIterator, ""));
    }

    @Test
    public void testJoinWithWhitespaceSeparator() {
        Iterator<String> elementsIterator = Arrays.asList("one", "two", "three").iterator();
        assertEquals("one two three", StringUtil.join(elementsIterator, " "));
    }

    @Test
    public void testJoinWithNumericElements() {
        Iterator<Integer> numericElementsIterator = Arrays.asList(1, 2, 3).iterator();
        assertEquals("1,2,3", StringUtil.join(numericElementsIterator, ","));
    }

    @Test
    public void testJoinWithMixedElements() {
        Iterator<Object> mixedElementsIterator = Arrays.<Object>asList("one", 2, "three").iterator();
        assertEquals("one,2,three", StringUtil.join(mixedElementsIterator, ","));
    }
}
