
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
        testList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    }

    @Test
    public void testNextWithinBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 5);
        assertTrue(iter.hasNext());
        assertEquals("b", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("c", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("d", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("e", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("f", iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNextOutOfBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 5);
        for (int i = 0; i < 5; i++) {
            iter.next();
        }
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }
}
