
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_previousTest {

    private SingletonListIterator<String> iterator;
    private static final String testValue = "testObject";

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testPreviousSuccess() {
        iterator.next();
        assertEquals(testValue, iterator.previous());
    }

    @Test
    public void testPreviousBeforeFirst() {
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        });
    }

    @Test
    public void testPreviousAfterRemove() {
        iterator.next();
        iterator.remove();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.previous();
        });
    }
}
