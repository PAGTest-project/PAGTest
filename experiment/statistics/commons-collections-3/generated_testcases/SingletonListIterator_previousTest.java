
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
        assertEquals(testValue, iterator.previous(), "Previous should return the single object");
    }

    @Test
    public void testPreviousNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> iterator.previous(), "Should throw NoSuchElementException if beforeFirst is true");
    }

    @Test
    public void testPreviousAfterRemove() {
        iterator.next(); // Move to the object
        iterator.remove(); // Remove the object
        assertThrows(NoSuchElementException.class, () -> iterator.previous(), "Should throw NoSuchElementException if removed is true");
    }
}
