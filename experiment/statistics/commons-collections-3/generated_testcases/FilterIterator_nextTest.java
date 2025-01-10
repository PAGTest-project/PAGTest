
package org.apache.commons.collections4.iterators;

import static org.apache.commons.collections4.functors.TruePredicate.truePredicate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterIterator_nextTest {

    private FilterIterator<String> filterIterator;
    private Iterator<String> iterator;
    private Predicate<String> predicate;

    @BeforeEach
    public void setUp() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        iterator = list.iterator();
        predicate = truePredicate();
        filterIterator = new FilterIterator<>(iterator, predicate);
    }

    @Test
    public void testNextWithValidElement() {
        assertTrue(filterIterator.hasNext());
        assertEquals("a", filterIterator.next());
    }

    @Test
    public void testNextWithNoSuchElementException() {
        // Consume all elements
        while (filterIterator.hasNext()) {
            filterIterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> filterIterator.next());
    }
}
