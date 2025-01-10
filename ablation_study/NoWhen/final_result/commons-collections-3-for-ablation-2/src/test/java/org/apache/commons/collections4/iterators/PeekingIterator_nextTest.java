
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @SuppressWarnings("unchecked")
    private PeekingIterator<String> makeObject() {
        return new PeekingIterator<>(testList.iterator());
    }

    private void validate(Iterator<String> iter, String... items) {
        for (String item : items) {
            assertTrue(iter.hasNext());
            assertEquals(item, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextWithPeek() {
        PeekingIterator<String> it = makeObject();
        assertEquals("a", it.peek());
        assertEquals("a", it.next());
        assertEquals("b", it.peek());
        assertEquals("b", it.next());
        assertEquals("c", it.peek());
        assertEquals("c", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testNextWithoutPeek() {
        PeekingIterator<String> it = makeObject();
        assertEquals("a", it.next());
        assertEquals("b", it.next());
        assertEquals("c", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testNextThrowsNoSuchElementException() {
        PeekingIterator<String> it = makeObject();
        it.next();
        it.next();
        it.next();
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void testNextAfterExhausted() {
        PeekingIterator<String> it = makeObject();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }
}
