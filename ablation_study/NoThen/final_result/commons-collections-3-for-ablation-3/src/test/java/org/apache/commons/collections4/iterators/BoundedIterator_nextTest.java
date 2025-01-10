
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoundedIterator_nextTest {

    private List<String> testList;

    @BeforeEach
    public void setUp() {
        testList = Arrays.asList("A", "B", "C", "D", "E");
    }

    @Test
    public void testNextWithinBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 3);
        assertTrue(iter.hasNext());
        assertEquals("B", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("C", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("D", iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextOutOfBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 3);
        iter.next(); // Move to "B"
        iter.next(); // Move to "C"
        iter.next(); // Move to "D"
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }

    @Test
    public void testNextWithOffsetGreaterThanSize() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 10, 4);
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }

    @Test
    public void testNextWithMaxZero() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 0, 0);
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }
}
