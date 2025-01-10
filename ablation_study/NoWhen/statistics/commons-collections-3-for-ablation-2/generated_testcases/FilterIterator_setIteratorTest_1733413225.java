
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
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        iterator = new FilterIterator<>(list.iterator(), truePredicate());
    }

    @Test
    public void testSetIterator() {
        Iterator<String> newIterator = Arrays.asList("d", "e", "f").iterator();
        iterator.setIterator(newIterator);
        assertEquals(newIterator, iterator.getIterator());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSetIteratorWithEmptyIterator() {
        Iterator<String> emptyIterator = Collections.emptyIterator();
        iterator.setIterator(emptyIterator);
        assertEquals(emptyIterator, iterator.getIterator());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSetIteratorAndCheckNext() {
        Iterator<String> newIterator = Arrays.asList("d", "e", "f").iterator();
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

    @Test
    public void testSetIteratorAndCheckHasNext() {
        Iterator<String> newIterator = Arrays.asList("d", "e", "f").iterator();
        iterator.setIterator(newIterator);
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}
