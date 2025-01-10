
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_setTest {

    private static final Object testValue = new Object();
    private SingletonListIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testSetAfterNext() {
        iterator.next();
        Object newValue = new Object();
        iterator.set(newValue);
        assertEquals(newValue, iterator.next(), "The set value should be returned by next()");
    }

    @Test
    public void testSetAfterRemove() {
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterator.set(new Object());
        }, "set() should throw IllegalStateException if the object has been removed");
    }

    @Test
    public void testSetWithoutNext() {
        assertThrows(IllegalStateException.class, () -> {
            iterator.set(new Object());
        }, "set() should throw IllegalStateException if next() has not been called");
    }

    @Test
    public void testSetAfterReset() {
        iterator.next();
        iterator.reset();
        assertThrows(IllegalStateException.class, () -> {
            iterator.set(new Object());
        }, "set() should throw IllegalStateException if reset() has been called");
    }
}
