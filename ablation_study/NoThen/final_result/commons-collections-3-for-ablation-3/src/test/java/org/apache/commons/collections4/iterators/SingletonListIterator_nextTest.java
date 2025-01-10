
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
        assertTrue(iterator.hasNext(), "Iterator should have next item");
        assertEquals(testValue, iterator.next(), "Iteration value is correct");
        assertFalse(iterator.hasNext(), "Iterator should have no next item");
    }

    @Test
    public void testNextAfterReset() {
        iterator.next();
        iterator.reset();
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertEquals(testValue, iterator.next(), "Iteration value is correct after reset");
    }

    @Test
    public void testNextAfterRemove() {
        iterator.next();
        iterator.remove();
        assertFalse(iterator.hasNext(), "Iterator should have no next item after remove");
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "NoSuchElementException must be thrown after remove");
    }

    @Test
    public void testNextWithoutHasNext() {
        iterator.next();
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "NoSuchElementException must be thrown without hasNext check");
    }

    @Test
    public void testNextAfterPrevious() {
        iterator.next();
        iterator.previous();
        assertTrue(iterator.hasNext(), "Iterator should have next item after previous");
        assertEquals(testValue, iterator.next(), "Iteration value is correct after previous");
    }
}
