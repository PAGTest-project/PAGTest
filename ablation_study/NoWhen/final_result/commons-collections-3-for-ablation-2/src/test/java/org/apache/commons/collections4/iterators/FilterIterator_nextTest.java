
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
    public void testNextWithNoElements() {
        iterator = new FilterIterator<>(Collections.emptyIterator(), truePredicate());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testNextWithPredicate() {
        List<String> list = new ArrayList<>(Arrays.asList("a", null, "b", null, "c"));
        iterator = new FilterIterator<>(list.iterator(), NotNullPredicate.notNullPredicate());
        for (String expected : array) {
            assertEquals(expected, iterator.next());
        }
    }

    @Test
    public void testNextWithNoMatchingPredicate() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        iterator = new FilterIterator<>(list.iterator(), NotNullPredicate.notNullPredicate());
        for (String expected : array) {
            assertEquals(expected, iterator.next());
        }
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
