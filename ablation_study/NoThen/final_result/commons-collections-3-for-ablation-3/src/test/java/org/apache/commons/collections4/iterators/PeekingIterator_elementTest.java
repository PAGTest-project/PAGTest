
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeekingIterator_elementTest {

    private List<String> testList;
    private static final String[] testArray = {"a", "b", "c"};

    @SuppressWarnings("unchecked")
    @BeforeEach
    protected void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList((String[]) testArray));
    }

    @Test
    public void testElementWithValidIterator() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertEquals("a", it.element());
    }

    @Test
    public void testElementWithExhaustedIterator() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        while (it.hasNext()) {
            it.next();
        }
        assertThrows(NoSuchElementException.class, () -> it.element());
    }

    @Test
    public void testElementAfterPeek() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertEquals("a", it.peek());
        assertEquals("a", it.element());
    }

    @Test
    public void testElementAfterNext() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertEquals("a", it.next());
        assertEquals("b", it.element());
    }
}
