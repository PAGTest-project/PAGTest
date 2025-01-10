
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectGraphIterator_nextTest {

    private ObjectGraphIterator<String> iterator;
    private List<String> elements;

    @BeforeEach
    public void setUp() {
        elements = new ArrayList<>();
        elements.add("Element1");
        elements.add("Element2");
        elements.add("Element3");
        iterator = new ObjectGraphIterator<>(elements.iterator());
    }

    @Test
    public void testNext_Success() {
        assertTrue(iterator.hasNext());
        assertEquals("Element1", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Element2", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Element3", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext_NoSuchElementException() {
        // Consume all elements
        iterator.next();
        iterator.next();
        iterator.next();

        // Attempt to get next element when none are left
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
