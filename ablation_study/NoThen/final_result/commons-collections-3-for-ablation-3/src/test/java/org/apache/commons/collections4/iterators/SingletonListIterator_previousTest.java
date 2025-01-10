
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
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
        iterator.next(); // Move to the object
        Object previousValue = iterator.previous();
        assertEquals(testValue, previousValue, "Previous value should be the same as the initial object");
    }

    @Test
    public void testPreviousBeforeFirst() {
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        }, "Calling previous before first should throw NoSuchElementException");
    }

    @Test
    public void testPreviousAfterRemove() {
        iterator.next(); // Move to the object
        iterator.remove(); // Remove the object
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        }, "Calling previous after remove should throw NoSuchElementException");
    }

    @Test
    public void testPreviousAfterReset() {
        iterator.next(); // Move to the object
        iterator.reset(); // Reset the iterator
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        }, "Calling previous after reset should throw NoSuchElementException");
    }
}
