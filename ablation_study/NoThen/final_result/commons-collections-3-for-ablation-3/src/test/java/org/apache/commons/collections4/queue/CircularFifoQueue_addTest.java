
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class CircularFifoQueue_addTest {

    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testAddElementSuccess() {
        assertTrue(queue.add("Element1"));
        assertEquals(1, queue.size());
        assertEquals("Element1", queue.peek());
    }

    @Test
    public void testAddElementNull() {
        assertThrows(NullPointerException.class, () -> queue.add(null));
    }

    @Test
    public void testAddElementAtFullCapacity() {
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");
        assertTrue(queue.isAtFullCapacity());
        assertTrue(queue.add("Element4"));
        assertEquals(3, queue.size());
        assertEquals("Element2", queue.peek());
    }

    @Test
    public void testAddElementWrapAround() {
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");
        queue.add("Element4");
        assertEquals(3, queue.size());
        assertEquals("Element2", queue.peek());
        assertEquals("Element2", queue.get(0));
        assertEquals("Element3", queue.get(1));
        assertEquals("Element4", queue.get(2));
    }

    @Test
    public void testAddElementAfterClear() {
        queue.add("Element1");
        queue.add("Element2");
        queue.clear();
        assertTrue(queue.add("Element3"));
        assertEquals(1, queue.size());
        assertEquals("Element3", queue.peek());
    }

    @Test
    public void testAddElementAfterRemove() {
        queue.add("Element1");
        queue.add("Element2");
        queue.remove();
        assertTrue(queue.add("Element3"));
        assertEquals(2, queue.size());
        assertEquals("Element2", queue.peek());
    }
}
