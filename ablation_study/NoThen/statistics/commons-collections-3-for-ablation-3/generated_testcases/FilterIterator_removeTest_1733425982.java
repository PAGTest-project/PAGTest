
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
    public void testRemoveAfterNext() {
        iterator.next(); // Move to the first element
        iterator.remove(); // Remove the first element
        assertFalse(Arrays.asList(array).contains("a"));
    }

    @Test
    public void testRemoveAfterHasNext() {
        iterator.hasNext(); // Check if there is a next element
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove(); // Attempt to remove should throw IllegalStateException
        });
    }

    @Test
    public void testRemoveWithoutNext() {
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove(); // Attempt to remove without calling next should throw IllegalStateException
        });
    }

    @Test
    public void testRemoveAfterEndOfIteration() {
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove(); // Attempt to remove after end of iteration should throw IllegalStateException
        });
    }

    private void verifyNoMoreElements() {
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }
}
