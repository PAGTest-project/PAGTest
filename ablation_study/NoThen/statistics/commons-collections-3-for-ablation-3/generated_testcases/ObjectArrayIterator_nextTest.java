
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectArrayIterator_nextTest {

    private String[] testArray;
    private ObjectArrayIterator<String> iterator;

    @BeforeEach
    public void setUp() {
        testArray = new String[] {"One", "Two", "Three"};
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
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    public void testNextAfterReset() {
        assertEquals("One", iterator.next());
        iterator.reset();
        assertEquals("One", iterator.next());
    }
}
