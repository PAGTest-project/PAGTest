
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterListIterator_previousTest {

    private FilterListIterator<Integer> filterListIterator;
    private ListIterator<Integer> listIterator;
    private Predicate<Integer> evenPred;
    private List<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        listIterator = list.listIterator();
        evenPred = x -> x % 2 == 0;
        filterListIterator = new FilterListIterator<>(listIterator, evenPred);
    }

    @Test
    public void testPreviousWithValidPreviousObject() {
        // Move the iterator to the end of the list
        while (filterListIterator.hasNext()) {
            filterListIterator.next();
        }

        // Move back one step to set previousObject
        filterListIterator.previous();

        // Now call previous again
        assertEquals(16, filterListIterator.previous());
    }

    @Test
    public void testPreviousWithNoPreviousObject() {
        // Ensure the iterator is at the start
        while (filterListIterator.hasPrevious()) {
            filterListIterator.previous();
        }

        // Attempt to call previous when no previous object is set
        assertThrows(NoSuchElementException.class, () -> filterListIterator.previous());
    }
}
