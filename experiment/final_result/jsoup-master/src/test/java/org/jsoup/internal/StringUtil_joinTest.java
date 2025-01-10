
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtil_joinTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testJoinEmptyIterator() {
        Iterator<String> emptyIterator = Collections.<String>emptyList().iterator();
        String result = StringUtil.join(emptyIterator, ",");
        assertEquals("", result);
    }

    @Test
    public void testJoinSingleElementIterator() {
        Iterator<String> singleElementIterator = Collections.singletonList("single").iterator();
        String result = StringUtil.join(singleElementIterator, ",");
        assertEquals("single", result);
    }

    @Test
    public void testJoinMultipleElementsIterator() {
        Iterator<String> multipleElementsIterator = Arrays.asList("one", "two", "three").iterator();
        String result = StringUtil.join(multipleElementsIterator, ",");
        assertEquals("one,two,three", result);
    }
}
