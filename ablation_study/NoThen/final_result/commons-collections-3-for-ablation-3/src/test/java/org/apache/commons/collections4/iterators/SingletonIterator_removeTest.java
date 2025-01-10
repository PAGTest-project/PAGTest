
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
    public void testRemoveAllowed() {
        iterator.next(); // Move to the element
        iterator.remove(); // Remove the element
        assertFalse(iterator.hasNext(), "Iterator should be empty after removal");
    }

    @Test
    public void testRemoveNotAllowed() {
        iterator = new SingletonIterator<>(testValue, false);
        iterator.next(); // Move to the element
        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveBeforeNext() {
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveAfterAlreadyRemoved() {
        iterator.next(); // Move to the element
        iterator.remove(); // Remove the element
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveAfterReset() {
        iterator.next(); // Move to the element
        iterator.reset(); // Reset the iterator
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }
}
