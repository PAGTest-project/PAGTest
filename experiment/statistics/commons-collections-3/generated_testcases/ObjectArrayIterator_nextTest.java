
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectArrayIterator_nextTest {

    private ObjectArrayIterator<String> iterator;
    private String[] testArray = {"One", "Two", "Three"};

    @BeforeEach
    public void setUp() {
        iterator = new ObjectArrayIterator<>(testArray);
    }

    @Test
    public void testNextWithElements() {
        assertEquals("One", iterator.next());
        assertEquals("Two", iterator.next());
        assertEquals("Three", iterator.next());
    }

    @Test
    public void testNextWithoutElements() {
        iterator.next();
        iterator.next();
        iterator.next();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
