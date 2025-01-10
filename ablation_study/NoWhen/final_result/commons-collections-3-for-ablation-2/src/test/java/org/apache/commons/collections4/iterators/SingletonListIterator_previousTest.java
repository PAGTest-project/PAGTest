
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_previousTest {

    private static final Object testValue = new Object();
    private SingletonListIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testPreviousSuccess() {
        iterator.next();
        assertEquals(testValue, iterator.previous(), "Previous value should be the same as the initial value");
    }

    @Test
    public void testPreviousBeforeFirst() {
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        }, "NoSuchElementException should be thrown if previous is called before first");
    }

    @Test
    public void testPreviousAfterRemove() {
        iterator.next();
        iterator.remove();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        }, "NoSuchElementException should be thrown if previous is called after remove");
    }

    @Test
    public void testPreviousAfterReset() {
        iterator.next();
        iterator.reset();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        }, "NoSuchElementException should be thrown if previous is called after reset");
    }
}
