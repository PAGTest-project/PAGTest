
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PushbackIterator_pushbackIteratorTest {

    private List<String> testList;

    @BeforeEach
    protected void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList("a", "b", "c"));
    }

    private PushbackIterator<String> makeObject() {
        return PushbackIterator.pushbackIterator(testList.iterator());
    }

    @Test
    public void testPushbackIteratorWithPushbackIterator() {
        PushbackIterator<String> originalIter = makeObject();
        PushbackIterator<String> resultIter = PushbackIterator.pushbackIterator(originalIter);
        assertSame(originalIter, resultIter);
    }

    @Test
    public void testPushbackIteratorWithRegularIterator() {
        PushbackIterator<String> resultIter = PushbackIterator.pushbackIterator(testList.iterator());
        assertNotNull(resultIter);
        assertTrue(resultIter.hasNext());
        assertEquals("a", resultIter.next());
    }

    @Test
    public void testPushbackIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            PushbackIterator.pushbackIterator(null);
        });
    }

    @Test
    public void testPushbackIteratorWithEmptyIterator() {
        testList = Collections.emptyList();
        PushbackIterator<String> resultIter = PushbackIterator.pushbackIterator(testList.iterator());
        assertNotNull(resultIter);
        assertFalse(resultIter.hasNext());
    }
}
