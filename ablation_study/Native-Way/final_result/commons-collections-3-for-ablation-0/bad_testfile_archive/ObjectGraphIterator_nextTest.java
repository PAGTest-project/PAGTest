
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        iterator = new ObjectGraphIterator<>(null, null);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNext_WithTransformer() {
        Transformer<String, String> transformer = input -> input + " transformed";
        iterator = new ObjectGraphIterator<>("A", transformer);

        assertTrue(iterator.hasNext());
        assertEquals("A transformed", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext_WithNestedIterators() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        List<String> list2 = new ArrayList<>();
        list2.add("C");
        List<Iterator<String>> nestedIterators = new ArrayList<>();
        nestedIterators.add(list1.iterator());
        nestedIterators.add(list2.iterator());

        iterator = new ObjectGraphIterator<>(nestedIterators.iterator());

        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
