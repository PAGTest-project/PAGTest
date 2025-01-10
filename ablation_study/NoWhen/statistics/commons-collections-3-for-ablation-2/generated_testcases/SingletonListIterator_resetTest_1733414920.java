
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_resetTest {

    private static final Object testValue = new Object();
    private SingletonListIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testResetInitialState() {
        // Given
        iterator.next();
        iterator.previous();
        iterator.reset();

        // Then
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertFalse(iterator.hasPrevious(), "Iterator should have no previous item after reset");
        assertEquals(0, iterator.nextIndex(), "Iteration next index after reset");
        assertEquals(-1, iterator.previousIndex(), "Iteration previous index after reset");
    }

    @Test
    public void testResetAfterRemove() {
        // Given
        iterator.next();
        iterator.remove();
        iterator.reset();

        // Then
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertFalse(iterator.hasPrevious(), "Iterator should have no previous item after reset");
        assertEquals(0, iterator.nextIndex(), "Iteration next index after reset");
        assertEquals(-1, iterator.previousIndex(), "Iteration previous index after reset");
    }

    @Test
    public void testResetAndNext() {
        // Given
        iterator.next();
        iterator.reset();

        // Then
        assertEquals(testValue, iterator.next(), "Next value should be correct after reset");
    }

    @Test
    public void testResetAndHasNext() {
        // Given
        iterator.next();
        iterator.reset();

        // Then
        assertTrue(iterator.hasNext(), "hasNext should return true after reset");
    }

    @Test
    public void testResetAndNextThrowsException() {
        // Given
        iterator.next();
        iterator.remove();
        iterator.reset();

        // Then
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        }, "NoSuchElementException should be thrown after reset and next call");
    }
}
