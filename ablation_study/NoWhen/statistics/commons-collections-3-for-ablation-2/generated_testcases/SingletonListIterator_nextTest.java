
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_nextTest {

    private static final Object testValue = new Object();
    private SingletonListIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testNextSuccess() {
        assertTrue(iterator.hasNext());
        assertEquals(testValue, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNextAfterReset() {
        assertEquals(testValue, iterator.next());
        iterator.reset();
        assertTrue(iterator.hasNext());
        assertEquals(testValue, iterator.next());
    }

    @Test
    public void testNextAfterRemove() {
        assertEquals(testValue, iterator.next());
        iterator.remove();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextAfterPrevious() {
        assertEquals(testValue, iterator.next());
        assertEquals(testValue, iterator.previous());
        assertTrue(iterator.hasNext());
        assertEquals(testValue, iterator.next());
    }

    @Test
    public void testNextThrowsNoSuchElementException() {
        assertEquals(testValue, iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
