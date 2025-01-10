
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonIterator_nextTest {

    private SingletonIterator<String> iterator;
    private static final String testValue = "testObject";

    @BeforeEach
    public void setUp() {
        iterator = new SingletonIterator<>(testValue);
    }

    @Test
    public void testNextSuccess() {
        assertTrue(iterator.hasNext());
        assertEquals(testValue, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNextNoSuchElementException() {
        iterator.next(); // Move to the element
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextAfterRemove() {
        iterator.next(); // Move to the element
        iterator.remove(); // Remove the element
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextAfterReset() {
        iterator.next(); // Move to the element
        iterator.reset(); // Reset the iterator
        assertTrue(iterator.hasNext());
        assertEquals(testValue, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
