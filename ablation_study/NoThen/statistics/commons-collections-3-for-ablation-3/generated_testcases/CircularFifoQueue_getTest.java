
package org.apache.commons.collections4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularFifoQueue_getTest {
    private CircularFifoQueue<String> queue;

    @BeforeEach
    public void setUp() {
        queue = new CircularFifoQueue<>(5);
    }

    @Test
    public void testGetValidIndex() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");

        assertEquals("1", queue.get(0));
        assertEquals("3", queue.get(2));
        assertEquals("5", queue.get(4));
    }

    @Test
    public void testGetInvalidIndex() {
        queue.add("1");
        queue.add("2");
        queue.add("3");

        assertThrows(NoSuchElementException.class, () -> queue.get(-1));
        assertThrows(NoSuchElementException.class, () -> queue.get(3));
    }

    @Test
    public void testGetAfterWrapAround() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        queue.add("7");

        assertEquals("3", queue.get(0));
        assertEquals("5", queue.get(2));
        assertEquals("7", queue.get(4));
    }

    @Test
    public void testGetEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> queue.get(0));
    }

    @Test
    public void testGetAfterClear() {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.clear();

        assertThrows(NoSuchElementException.class, () -> queue.get(0));
    }
}
