
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

public class FilterListIterator_nextTest {

    private List<Integer> list;
    private Predicate<Integer> truePred;
    private FilterListIterator<Integer> filterListIterator;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }

        truePred = x -> true;

        filterListIterator = new FilterListIterator<>(list.listIterator(), truePred);
    }

    @Test
    public void testNextWithValidElement() {
        assertTrue(filterListIterator.hasNext());
        assertEquals(Integer.valueOf(0), filterListIterator.next());
    }

    @Test
    public void testNextWithNoSuchElementException() {
        // Consume all elements
        while (filterListIterator.hasNext()) {
            filterListIterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> filterListIterator.next());
    }
}
