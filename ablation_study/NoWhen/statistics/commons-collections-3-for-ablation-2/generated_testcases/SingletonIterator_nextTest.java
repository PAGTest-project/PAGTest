
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
        assertEquals(testValue, iterator.next(), "Iteration value is correct");
    }

    @Test
    public void testNextAfterReset() {
        iterator.next();
        iterator.reset();
        assertEquals(testValue, iterator.next(), "Iteration value is correct after reset");
    }

    @Test
    public void testNextAfterRemove() {
        iterator.next();
        iterator.remove();
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "NoSuchElementException must be thrown after remove");
    }

    @Test
    public void testNextWithoutReset() {
        iterator.next();
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "NoSuchElementException must be thrown without reset");
    }
}
