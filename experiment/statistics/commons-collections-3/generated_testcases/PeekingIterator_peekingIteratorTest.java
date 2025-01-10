
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

    private List<String> testList;
    private static final String[] testArray = {"a", "b", "c"};

    @SuppressWarnings("unchecked")
    @BeforeEach
    protected void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList((String[]) testArray));
    }

    @Test
    public void testPeekingIteratorWithStandardIterator() {
        Iterator<String> standardIterator = testList.iterator();
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(standardIterator);

        assertTrue(peekingIterator.hasNext());
        assertEquals("a", peekingIterator.peek());
        assertEquals("a", peekingIterator.next());
        assertEquals("b", peekingIterator.peek());
        assertEquals("b", peekingIterator.next());
        assertEquals("c", peekingIterator.peek());
        assertEquals("c", peekingIterator.next());
        assertFalse(peekingIterator.hasNext());
        assertNull(peekingIterator.peek());
        assertThrows(NoSuchElementException.class, () -> peekingIterator.next());
    }

    @Test
    public void testPeekingIteratorWithPeekingIterator() {
        Iterator<String> standardIterator = testList.iterator();
        PeekingIterator<String> originalPeekingIterator = new PeekingIterator<>(standardIterator);
        PeekingIterator<String> peekingIterator = PeekingIterator.peekingIterator(originalPeekingIterator);

        assertSame(originalPeekingIterator, peekingIterator);
        assertTrue(peekingIterator.hasNext());
        assertEquals("a", peekingIterator.peek());
        assertEquals("a", peekingIterator.next());
        assertEquals("b", peekingIterator.peek());
        assertEquals("b", peekingIterator.next());
        assertEquals("c", peekingIterator.peek());
        assertEquals("c", peekingIterator.next());
        assertFalse(peekingIterator.hasNext());
        assertNull(peekingIterator.peek());
        assertThrows(NoSuchElementException.class, () -> peekingIterator.next());
    }

    @Test
    public void testPeekingIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> PeekingIterator.peekingIterator(null));
    }
}
