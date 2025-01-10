
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

public class FilterIterator_removeTest {

    private FilterIterator<String> iterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        iterator = new FilterIterator<>(list.iterator(), truePredicate());
    }

    @Test
    public void testRemoveSuccess() {
        iterator.next(); // Move to the first element
        iterator.remove(); // Remove the last returned element
        assertFalse(list.contains("a"));
    }

    @Test
    public void testRemoveWithoutNextCall() {
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testRemoveAfterHasNextCall() {
        iterator.hasNext(); // Call hasNext to set nextObjectSet to true
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testRemoveOnEmptyIterator() {
        iterator = new FilterIterator<>(Collections.emptyIterator(), truePredicate());
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }
}
