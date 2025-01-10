
package org.apache.commons.collections4.iterators;

import static org.apache.commons.collections4.functors.TruePredicate.truePredicate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterIterator_setIteratorTest {

    private FilterIterator<String> iterator;
    private String[] array;

    @BeforeEach
    public void setUp() {
        array = new String[] { "a", "b", "c" };
        initIterator();
    }

    private void initIterator() {
        List<String> list = new ArrayList<>(Arrays.asList(array));
        iterator = new FilterIterator<>(list.iterator(), truePredicate());
    }

    @Test
    public void testSetIterator() {
        List<String> newList = new ArrayList<>(Arrays.asList("d", "e", "f"));
        Iterator<String> newIterator = newList.iterator();
        iterator.setIterator(newIterator);
        assertEquals(newIterator, iterator.getIterator());
        assertFalse(iterator.hasNext()); // Reset the iterator to check hasNext
        iterator.setIterator(newList.iterator()); // Reset the iterator to check hasNext
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSetIteratorWithEmptyIterator() {
        List<String> emptyList = Collections.emptyList();
        Iterator<String> emptyIterator = emptyList.iterator();
        iterator.setIterator(emptyIterator);
        assertEquals(emptyIterator, iterator.getIterator());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSetIteratorAndCheckNext() {
        List<String> newList = new ArrayList<>(Arrays.asList("d", "e", "f"));
        Iterator<String> newIterator = newList.iterator();
        iterator.setIterator(newIterator);
        assertTrue(iterator.hasNext());
        assertEquals("d", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("e", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("f", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSetIteratorAndCheckNextWithPredicate() {
        List<String> newList = new ArrayList<>(Arrays.asList("d", "e", "f"));
        Iterator<String> newIterator = newList.iterator();
        Predicate<String> notNullPredicate = NotNullPredicate.notNullPredicate();
        iterator.setIterator(newIterator);
        iterator.setPredicate(notNullPredicate);
        assertTrue(iterator.hasNext());
        assertEquals("d", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("e", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("f", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSetIteratorAndCheckNextWithNoSuchElementException() {
        List<String> newList = new ArrayList<>(Arrays.asList("d", "e", "f"));
        Iterator<String> newIterator = newList.iterator();
        iterator.setIterator(newIterator);
        assertTrue(iterator.hasNext());
        assertEquals("d", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("e", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("f", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    private void verifyNoMoreElements() {
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
