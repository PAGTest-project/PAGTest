
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_nextTest {

    private SingletonListIterator<String> iterator;
    private static final String testValue = "testObject";

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
    public void testNextNoSuchElementException() {
        iterator.next(); // Move to the next element
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "NoSuchElementException must be thrown");
    }

    @Test
    public void testNextAfterRemove() {
        iterator.next(); // Move to the next element
        iterator.remove(); // Remove the element
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "NoSuchElementException must be thrown after remove");
    }

    @Test
    public void testNextAfterReset() {
        iterator.next(); // Move to the next element
        iterator.reset(); // Reset the iterator
        assertTrue(iterator.hasNext(), "Iterator should have next item after reset");
        assertEquals(testValue, iterator.next(), "Iteration value is correct after reset");
    }

    @Test
    public void testNextAfterSet() {
        iterator.next(); // Move to the next element
        iterator.set("newTestObject"); // Set a new value
        iterator.reset(); // Reset the iterator
        assertTrue(iterator.hasNext(), "Iterator should have next item after set and reset");
        assertEquals("newTestObject", iterator.next(), "Iteration value is correct after set and reset");
    }
}
