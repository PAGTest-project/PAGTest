
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_resetTest {

    private SingletonListIterator<String> iterator;
    private static final String testValue = "testObject";

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testReset() {
        // Given: An iterator initialized with a single object
        // When: next() is called to move the iterator forward
        iterator.next();
        // And: reset() is called to reset the iterator
        iterator.reset();

        // Then: The iterator should be back to its initial state
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertFalse(iterator.hasPrevious(), "Iterator should have no previous item after reset");
        assertEquals(0, iterator.nextIndex(), "Iteration next index after reset");
        assertEquals(-1, iterator.previousIndex(), "Iteration previous index after reset");
    }
}
