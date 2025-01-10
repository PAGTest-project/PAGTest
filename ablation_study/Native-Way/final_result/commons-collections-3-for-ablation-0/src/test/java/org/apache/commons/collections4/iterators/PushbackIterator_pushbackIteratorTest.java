
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void testPushbackIteratorWithPushbackIterator() {
        PushbackIterator<String> originalIter = new PushbackIterator<>(testList.iterator());
        PushbackIterator<String> iter = PushbackIterator.pushbackIterator(originalIter);
        assertSame(originalIter, iter);
    }

    @Test
    public void testPushbackIteratorWithRegularIterator() {
        Iterator<String> regularIter = testList.iterator();
        PushbackIterator<String> iter = PushbackIterator.pushbackIterator(regularIter);
        assertNotNull(iter);
        assertNotSame(regularIter, iter);
    }

    @Test
    public void testPushbackIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            PushbackIterator.pushbackIterator(null);
        });
    }
}
