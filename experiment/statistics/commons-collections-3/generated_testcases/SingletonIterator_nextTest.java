
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonIterator_nextTest {

    private SingletonIterator<String> iterator;
    private static final String testValue = "testObject";

    @BeforeEach
    public void setUp() {
        iterator = new SingletonIterator<>(testValue, true);
    }

    @Test
    public void testNextSuccess() {
        assertEquals(testValue, iterator.next(), "Next should return the single object");
    }

    @Test
    public void testNextNoSuchElementException() {
        iterator.next(); // Move to the object
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "Next should throw NoSuchElementException after the object has been returned");
    }

    @Test
    public void testNextNoSuchElementExceptionAfterRemove() {
        iterator.next(); // Move to the object
        iterator.remove(); // Remove the object
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "Next should throw NoSuchElementException after the object has been removed");
    }
}
