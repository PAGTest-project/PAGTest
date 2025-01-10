
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonIterator_removeTest {

    private static final Object testValue = new Object();
    private SingletonIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonIterator<>(testValue, true);
    }

    @Test
    public void testRemoveSuccess() {
        iterator.next(); // Move to the object
        iterator.remove(); // Remove the object
        assertNull(iterator.next()); // Ensure the object is removed
    }

    @Test
    public void testRemoveNotAllowed() {
        iterator = new SingletonIterator<>(testValue, false);
        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveBeforeNext() {
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveAfterAlreadyRemoved() {
        iterator.next(); // Move to the object
        iterator.remove(); // Remove the object
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }
}
