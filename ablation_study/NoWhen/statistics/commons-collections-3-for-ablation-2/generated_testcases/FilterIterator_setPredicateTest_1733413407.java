
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

public class FilterIterator_setPredicateTest {

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
    public void testSetPredicate() {
        Predicate<String> notNullPredicate = NotNullPredicate.notNullPredicate();
        iterator.setPredicate(notNullPredicate);
        assertEquals(notNullPredicate, iterator.getPredicate());
        assertFalse(iterator.hasNext()); // Since the array elements are not null, hasNext should return false
    }

    @Test
    public void testSetPredicateWithNull() {
        assertThrows(NullPointerException.class, () -> {
            iterator.setPredicate(null);
        });
    }

    @Test
    public void testSetPredicateAndHasNext() {
        Predicate<String> notNullPredicate = NotNullPredicate.notNullPredicate();
        iterator.setPredicate(notNullPredicate);
        assertTrue(iterator.hasNext()); // Since the array elements are not null, hasNext should return true
    }

    @Test
    public void testSetPredicateAndNext() {
        Predicate<String> notNullPredicate = NotNullPredicate.notNullPredicate();
        iterator.setPredicate(notNullPredicate);
        for (String element : array) {
            assertEquals(element, iterator.next());
        }
    }

    @Test
    public void testSetPredicateAndNextWithNoSuchElementException() {
        Predicate<String> notNullPredicate = NotNullPredicate.notNullPredicate();
        iterator.setPredicate(notNullPredicate);
        for (String element : array) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }
}
