
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testAddElementWhenFull() {
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");
        assertTrue(queue.add("Element4"));
        assertEquals(3, queue.size());
        assertEquals("Element2", queue.peek());
    }

    @Test
    public void testAddNullElement() {
        assertThrows(NullPointerException.class, () -> queue.add(null));
    }

    @Test
    public void testAddElementAndCheckFullCapacity() {
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");
        assertTrue(queue.isAtFullCapacity());
        queue.add("Element4");
        assertTrue(queue.isAtFullCapacity()); // Fixed assertion
    }

    @Test
    public void testAddElementAndCheckSize() {
        queue.add("Element1");
        queue.add("Element2");
        assertEquals(2, queue.size());
        queue.add("Element3");
        assertEquals(3, queue.size());
        queue.add("Element4");
        assertEquals(3, queue.size());
    }

    @Test
    public void testAddElementAndRemove() {
        queue.add("Element1");
        queue.add("Element2");
        queue.add("Element3");
        assertEquals("Element1", queue.remove());
        assertEquals(2, queue.size());
        queue.add("Element4");
        assertEquals("Element2", queue.remove());
        assertEquals(2, queue.size());
    }

    @Test
    public void testAddElementAndClear() {
        queue.add("Element1");
        queue.add("Element2");
        queue.clear();
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }
}
