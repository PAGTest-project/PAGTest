
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_iteratorTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testIteratorHasNext() {
        queue.add("1");
        queue.add("2");
        Iterator<String> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        queue.add("1");
        queue.add("2");
        Iterator<String> iterator = queue.iterator();
        assertEquals("1", iterator.next());
        assertEquals("2", iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.remove();
        assertEquals("[2, 3]", queue.toString());
        iterator.next();
        iterator.remove();
        assertEquals("[3]", queue.toString());
        iterator.next();
        iterator.remove();
        assertEquals("[]", queue.toString());
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void testIteratorRemoveFirstElement() {
        queue.add("1");
        queue.add("2");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.remove();
        assertEquals("[2]", queue.toString());
    }

    @Test
    public void testIteratorRemoveLastElement() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals("[1, 2]", queue.toString());
    }

    @Test
    public void testIteratorRemoveMiddleElement() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals("[1, 3]", queue.toString());
    }

    @Test
    public void testIteratorRemoveWithWrapAround() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals("[4, 5, 6]", queue.toString());
    }
}
