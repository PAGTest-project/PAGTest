
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeekingIterator_peekingIteratorTest {

    private Iterator<String> iterator;

    @BeforeEach
    public void setUp() {
        List<String> list = Arrays.asList("A", "B", "C");
        iterator = list.iterator();
    }

    @Test
    public void testPeekingIteratorWithRegularIterator() {
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(iterator);
        assertTrue(peekingIterator.hasNext());
        assertEquals("A", peekingIterator.peek());
        assertEquals("A", peekingIterator.next());
        assertEquals("B", peekingIterator.peek());
        assertEquals("B", peekingIterator.next());
        assertEquals("C", peekingIterator.peek());
        assertEquals("C", peekingIterator.next());
        assertFalse(peekingIterator.hasNext());
        assertNull(peekingIterator.peek());
        assertThrows(NoSuchElementException.class, () -> peekingIterator.next());
    }

    @Test
    public void testPeekingIteratorWithPeekingIterator() {
        PeekingIterator<String> originalPeekingIterator = PeekingIterator.peekingIterator(iterator);
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(originalPeekingIterator);
        assertSame(originalPeekingIterator, peekingIterator);
    }

    @Test
    public void testPeekingIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> PeekingIterator.peekingIterator(null));
    }

    @Test
    public void testPeekingIteratorWithEmptyIterator() {
        Iterator<String> emptyIterator = makeEmptyIterator();
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(emptyIterator);
        assertFalse(peekingIterator.hasNext());
        assertNull(peekingIterator.peek());
        assertThrows(NoSuchElementException.class, () -> peekingIterator.next());
    }

    private Iterator<String> makeEmptyIterator() {
        return new ArrayList<String>().iterator();
    }
}
