
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

    @BeforeEach
    public void setUp() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        iterator = new ObjectGraphIterator<>(list.iterator());
    }

    @Test
    public void testNext_Success() {
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext_NoSuchElementException() {
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNext_WithTransformer() {
        Transformer<String, String> transformer = input -> input + " Transformed";
        iterator = new ObjectGraphIterator<>("A", transformer);

        assertTrue(iterator.hasNext());
        assertEquals("A Transformed", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext_EmptyIterator() {
        iterator = new ObjectGraphIterator<>(new ArrayList<String>().iterator());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
