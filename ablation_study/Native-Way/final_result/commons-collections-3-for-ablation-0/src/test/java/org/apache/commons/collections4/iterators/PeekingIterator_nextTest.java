
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeekingIterator_nextTest {

    private PeekingIterator<String> peekingIterator;
    private List<String> testList;

    @BeforeEach
    public void setUp() {
        testList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        peekingIterator = new PeekingIterator<>(testList.iterator());
    }

    @Test
    public void testNextWithElements() {
        assertEquals("A", peekingIterator.next());
        assertEquals("B", peekingIterator.next());
        assertEquals("C", peekingIterator.next());
    }

    @Test
    public void testNextWithEmptyIterator() {
        Iterator<String> emptyIterator = new ArrayList<String>().iterator();
        PeekingIterator<String> emptyPeekingIterator = new PeekingIterator<>(emptyIterator);
        assertThrows(NoSuchElementException.class, () -> {
            emptyPeekingIterator.next();
        });
    }

    @Test
    public void testNextAfterPeek() {
        assertEquals("A", peekingIterator.peek());
        assertEquals("A", peekingIterator.next());
        assertEquals("B", peekingIterator.peek());
        assertEquals("B", peekingIterator.next());
    }

    @Test
    public void testNextAfterElement() {
        assertEquals("A", peekingIterator.element());
        assertEquals("A", peekingIterator.next());
        assertEquals("B", peekingIterator.element());
        assertEquals("B", peekingIterator.next());
    }

    @Test
    public void testNextAfterHasNext() {
        assertTrue(peekingIterator.hasNext());
        assertEquals("A", peekingIterator.next());
        assertTrue(peekingIterator.hasNext());
        assertEquals("B", peekingIterator.next());
        assertTrue(peekingIterator.hasNext());
        assertEquals("C", peekingIterator.next());
        assertFalse(peekingIterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            peekingIterator.next();
        });
    }
}
