
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonIterator_nextTest {

    private static final Object testValue = new Object();
    private SingletonIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new SingletonIterator<>(testValue, true);
    }

    @Test
    public void testNextSuccess() {
        iterator.hasNext(); // Ensure hasNext is true
        assertEquals(testValue, iterator.next(), "Next should return the test value");
    }

    @Test
    public void testNextAfterRemove() {
        iterator.hasNext(); // Ensure hasNext is true
        iterator.next();
        iterator.remove();
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "Next should throw NoSuchElementException after remove");
    }

    @Test
    public void testNextAfterReset() {
        iterator.hasNext(); // Ensure hasNext is true
        iterator.next();
        iterator.reset();
        assertEquals(testValue, iterator.next(), "Next should return the test value after reset");
    }

    @Test
    public void testNextWithoutHasNext() {
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "Next should throw NoSuchElementException without calling hasNext first");
    }
}
