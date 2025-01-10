
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
    public void testResetAfterInitialization() {
        iterator.reset();
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertFalse(iterator.hasPrevious(), "Iterator should have no previous item after reset");
        assertEquals(0, iterator.nextIndex(), "Iteration next index after reset");
        assertEquals(-1, iterator.previousIndex(), "Iteration previous index after reset");
    }

    @Test
    public void testResetAfterNextCall() {
        iterator.next();
        iterator.reset();
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertFalse(iterator.hasPrevious(), "Iterator should have no previous item after reset");
        assertEquals(0, iterator.nextIndex(), "Iteration next index after reset");
        assertEquals(-1, iterator.previousIndex(), "Iteration previous index after reset");
    }

    @Test
    public void testResetAfterRemove() {
        iterator.next();
        iterator.remove();
        iterator.reset();
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertFalse(iterator.hasPrevious(), "Iterator should have no previous item after reset");
        assertEquals(0, iterator.nextIndex(), "Iteration next index after reset");
        assertEquals(-1, iterator.previousIndex(), "Iteration previous index after reset");
    }

    @Test
    public void testResetAfterSet() {
        iterator.next();
        iterator.set(new Object());
        iterator.reset();
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertFalse(iterator.hasPrevious(), "Iterator should have no previous item after reset");
        assertEquals(0, iterator.nextIndex(), "Iteration next index after reset");
        assertEquals(-1, iterator.previousIndex(), "Iteration previous index after reset");
    }
}
