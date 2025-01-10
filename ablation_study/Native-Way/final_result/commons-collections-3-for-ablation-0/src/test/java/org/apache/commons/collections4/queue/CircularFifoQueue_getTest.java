
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
        queue = new CircularFifoQueue<>(3);
    }

    @Test
    public void testGetValidIndex() {
        queue.add("A");
        queue.add("B");
        queue.add("C");

        assertEquals("A", queue.get(0));
        assertEquals("B", queue.get(1));
        assertEquals("C", queue.get(2));
    }

    @Test
    public void testGetInvalidIndex() {
        queue.add("A");
        queue.add("B");

        assertThrows(NoSuchElementException.class, () -> queue.get(-1));
        assertThrows(NoSuchElementException.class, () -> queue.get(2));
    }

    @Test
    public void testGetAfterRemove() {
        queue.add("A");
        queue.add("B");
        queue.add("C");

        queue.remove();
        queue.remove();

        assertEquals("C", queue.get(0));
    }

    @Test
    public void testGetWithWrapAround() {
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D"); // This will wrap around and remove "A"

        assertEquals("B", queue.get(0));
        assertEquals("C", queue.get(1));
        assertEquals("D", queue.get(2));
    }

    @Test
    public void testGetEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> queue.get(0));
    }
}
