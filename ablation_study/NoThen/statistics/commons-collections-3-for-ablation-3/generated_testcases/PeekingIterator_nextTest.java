
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

public class PeekingIterator_nextTest {

    private List<String> testList;
    private static final String[] testArray = {"a", "b", "c"};

    @SuppressWarnings("unchecked")
    @BeforeEach
    protected void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList((String[]) testArray));
    }

    @Test
    public void testNextWithPeek() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertEquals("a", it.peek());
        assertEquals("a", it.next());
        assertEquals("b", it.peek());
        assertEquals("b", it.next());
        assertEquals("c", it.peek());
        assertEquals("c", it.next());
    }

    @Test
    public void testNextWithoutPeek() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertEquals("a", it.next());
        assertEquals("b", it.next());
        assertEquals("c", it.next());
    }

    @Test
    public void testNextThrowsNoSuchElementException() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        while (it.hasNext()) {
            it.next();
        }
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void testNextAfterElement() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertEquals("a", it.element());
        assertEquals("a", it.next());
        assertEquals("b", it.element());
        assertEquals("b", it.next());
        assertEquals("c", it.element());
        assertEquals("c", it.next());
    }

    @Test
    public void testNextAfterPeek() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertEquals("a", it.peek());
        assertEquals("a", it.next());
        assertEquals("b", it.peek());
        assertEquals("b", it.next());
        assertEquals("c", it.peek());
        assertEquals("c", it.next());
    }
}
