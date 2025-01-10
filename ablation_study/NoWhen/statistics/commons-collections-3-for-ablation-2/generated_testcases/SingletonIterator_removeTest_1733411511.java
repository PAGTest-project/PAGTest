
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
        iterator.next();
        iterator.remove();
        assertNull(iterator.next());
    }

    @Test
    public void testRemoveNotAllowed() {
        iterator = new SingletonIterator<>(testValue, false);
        iterator.next();
        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveBeforeNext() {
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveAfterReset() {
        iterator.next();
        iterator.reset();
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveTwice() {
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }
}
