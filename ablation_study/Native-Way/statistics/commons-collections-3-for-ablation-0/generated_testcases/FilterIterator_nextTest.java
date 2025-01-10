
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

public class FilterIterator_nextTest {

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
    public void testNextWithElements() {
        for (String expected : array) {
            assertEquals(expected, iterator.next());
        }
    }

    @Test
    public void testNextWithoutElements() {
        iterator = new FilterIterator<>(Collections.emptyIterator(), truePredicate());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextWithPredicate() {
        iterator = new FilterIterator<>(Arrays.asList("a", null, "b").iterator(), NotNullPredicate.INSTANCE);
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextAfterHasNext() {
        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("b", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("c", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextAfterSetIterator() {
        iterator.setIterator(Arrays.asList("d", "e").iterator());
        assertEquals("d", iterator.next());
        assertEquals("e", iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextAfterSetPredicate() {
        iterator.setPredicate(NotNullPredicate.INSTANCE);
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("c", iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
