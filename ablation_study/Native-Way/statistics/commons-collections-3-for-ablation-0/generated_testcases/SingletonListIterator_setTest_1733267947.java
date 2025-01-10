
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
    public void testSetSuccess() {
        iterator.next();
        Object newValue = new Object();
        iterator.set(newValue);
        assertEquals(newValue, iterator.next());
    }

    @Test
    public void testSetWithoutNextCall() {
        assertThrows(IllegalStateException.class, () -> {
            iterator.set(new Object());
        });
    }

    @Test
    public void testSetAfterRemove() {
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterator.set(new Object());
        });
    }
}
