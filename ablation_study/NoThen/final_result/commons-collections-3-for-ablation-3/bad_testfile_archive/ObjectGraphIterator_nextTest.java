
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectGraphIterator_nextTest {

    private ObjectGraphIterator<String> iterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        iterator = new ObjectGraphIterator<>(list.iterator());
    }

    @Test
    public void testNext_withElements() {
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext_noElements() {
        iterator = new ObjectGraphIterator<>(new ArrayList<String>().iterator());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNext_withTransformer() {
        Transformer<String, String> transformer = input -> input + " transformed";
        iterator = new ObjectGraphIterator<>(list.iterator(), transformer);

        assertTrue(iterator.hasNext());
        assertEquals("A transformed", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B transformed", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C transformed", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext_withNestedIterators() {
        List<Iterator<String>> nestedList = new ArrayList<>();
        nestedList.add(list.iterator());
        nestedList.add(new ArrayList<String>().iterator());
        nestedList.add(list.iterator());

        iterator = new ObjectGraphIterator<>(nestedList.iterator());

        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
