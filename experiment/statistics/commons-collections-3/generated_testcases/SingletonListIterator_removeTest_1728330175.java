
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_removeTest {

    private SingletonListIterator<String> iterator;
    private static final String testValue = "testObject";

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testRemoveSuccess() {
        iterator.next();
        iterator.remove();
        assertNull(iterator.next());
        assertTrue(iterator.hasPrevious());
    }

    @Test
    public void testRemoveWithoutNextCall() {
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveTwice() {
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }
}
