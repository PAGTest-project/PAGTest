
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_removeTest {

    private static final Object testValue = new Object();
    private SingletonListIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testRemoveSuccess() {
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
    }

    @Test
    public void testRemoveWithoutNextCall() {
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testRemoveTwice() {
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testRemoveAfterReset() {
        iterator.next();
        iterator.reset();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testRemoveAfterSet() {
        iterator.next();
        iterator.set(new Object());
        iterator.remove();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
    }
}
