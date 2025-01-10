
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoundedIterator_hasNextTest {

    private List<String> testList;

    @BeforeEach
    public void setUp() {
        testList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    }

    @Test
    public void testHasNextWithinBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 3);
        assertTrue(iter.hasNext());
        assertEquals("b", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("c", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("d", iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextOutOfBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 5, 3);
        assertTrue(iter.hasNext());
        assertEquals("f", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("g", iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithZeroMax() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 0, 0);
        assertFalse(iter.hasNext());
    }
}
