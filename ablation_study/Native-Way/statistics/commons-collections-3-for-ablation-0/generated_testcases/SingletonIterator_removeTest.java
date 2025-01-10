
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonIterator_removeTest {

    private SingletonIterator<Object> iterator;
    private static final Object testValue = new Object();

    @BeforeEach
    public void setUp() {
        iterator = new SingletonIterator<>(testValue, true);
    }

    @Test
    public void testRemoveSuccess() {
        iterator.next();
        iterator.remove();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveNotAllowed() {
        iterator = new SingletonIterator<>(testValue, false);
        iterator.next();
        assertThrows(UnsupportedOperationException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testRemoveBeforeNext() {
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testRemoveAfterAlreadyRemoved() {
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }
}
