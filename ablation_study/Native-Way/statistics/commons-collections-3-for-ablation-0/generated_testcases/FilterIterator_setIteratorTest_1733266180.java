
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
        List<String> newList = Arrays.asList("d", "e", "f");
        iterator.setIterator(newList.iterator());
        assertEquals(newList.iterator().next(), iterator.getIterator().next());
        assertFalse(iterator.hasNext()); // Since the new list is empty
    }

    @Test
    public void testSetIteratorWithEmptyIterator() {
        List<String> emptyList = Collections.emptyList();
        iterator.setIterator(emptyList.iterator());
        assertEquals(emptyList.iterator(), iterator.getIterator());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSetIteratorWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            iterator.setIterator(null);
            iterator.hasNext();
        });
    }

    @Test
    public void testSetIteratorResetsNextObject() {
        assertTrue(iterator.hasNext());
        iterator.next();
        List<String> newList = Arrays.asList("d", "e", "f");
        iterator.setIterator(newList.iterator());
        assertFalse(iterator.hasNext()); // Since the new list is empty
    }
}
