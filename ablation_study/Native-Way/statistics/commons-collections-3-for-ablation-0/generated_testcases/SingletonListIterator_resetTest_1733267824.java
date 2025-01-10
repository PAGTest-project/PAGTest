
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_resetTest {

    private static final Object testValue = new Object();
    private SingletonListIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testResetInitialState() {
        // Initial state
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());

        // Call reset
        iterator.reset();

        // Verify reset state
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());
    }

    @Test
    public void testResetAfterNext() {
        // Move to the next element
        iterator.next();

        // State after next
        assertFalse(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
        assertEquals(1, iterator.nextIndex());
        assertEquals(0, iterator.previousIndex());

        // Call reset
        iterator.reset();

        // Verify reset state
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());
    }

    @Test
    public void testResetAfterPrevious() {
        // Move to the next element
        iterator.next();
        // Move to the previous element
        iterator.previous();

        // State after previous
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());

        // Call reset
        iterator.reset();

        // Verify reset state
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());
    }

    @Test
    public void testResetAfterRemove() {
        // Move to the next element
        iterator.next();
        // Remove the element
        iterator.remove();

        // State after remove
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());

        // Call reset
        iterator.reset();

        // Verify reset state
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());
    }
}
