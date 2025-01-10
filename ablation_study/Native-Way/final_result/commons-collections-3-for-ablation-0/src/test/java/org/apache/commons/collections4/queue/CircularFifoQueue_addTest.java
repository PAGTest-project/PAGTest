
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
        assertTrue(queue.add("A"));
        assertTrue(queue.add("B"));
        assertTrue(queue.add("C"));
        assertEquals(3, queue.size());
    }

    @Test
    public void testAddElementWhenFull() {
        queue.add("A");
        queue.add("B");
        queue.add("C");
        assertTrue(queue.add("D"));
        assertEquals(3, queue.size());
        assertEquals("B", queue.get(0));
        assertEquals("C", queue.get(1));
        assertEquals("D", queue.get(2));
    }

    @Test
    public void testAddNullElement() {
        assertThrows(NullPointerException.class, () -> {
            queue.add(null);
        });
    }

    @Test
    public void testAddElementWrapAround() {
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.remove();
        queue.add("D");
        assertEquals("B", queue.get(0));
        assertEquals("C", queue.get(1));
        assertEquals("D", queue.get(2));
    }

    @Test
    public void testAddElementWhenQueueIsEmpty() {
        assertTrue(queue.add("A"));
        assertEquals(1, queue.size());
        assertEquals("A", queue.get(0));
    }

    @Test
    public void testAddElementWhenQueueHasOneElement() {
        queue.add("A");
        assertTrue(queue.add("B"));
        assertEquals(2, queue.size());
        assertEquals("A", queue.get(0));
        assertEquals("B", queue.get(1));
    }

    @Test
    public void testAddElementWhenQueueHasTwoElements() {
        queue.add("A");
        queue.add("B");
        assertTrue(queue.add("C"));
        assertEquals(3, queue.size());
        assertEquals("A", queue.get(0));
        assertEquals("B", queue.get(1));
        assertEquals("C", queue.get(2));
    }

    @Test
    public void testAddElementWhenQueueIsFullAndRemoveIsCalled() {
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.remove();
        assertTrue(queue.add("D"));
        assertEquals(3, queue.size());
        assertEquals("B", queue.get(0));
        assertEquals("C", queue.get(1));
        assertEquals("D", queue.get(2));
    }
}
