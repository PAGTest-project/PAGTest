
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterListIterator_previousTest {

    private List<Integer> list;
    private Predicate<Integer> evenPred;
    private Predicate<Integer> oddPred;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }

        evenPred = x -> x % 2 == 0;
        oddPred = x -> x % 2 != 0;
    }

    @Test
    public void testPreviousWithEvenPredicate() {
        final FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), evenPred);
        // Move to the end of the list
        while (filtered.hasNext()) {
            filtered.next();
        }
        // Test previous
        assertEquals(18, filtered.previous());
        assertEquals(16, filtered.previous());
        assertEquals(14, filtered.previous());
    }

    @Test
    public void testPreviousWithOddPredicate() {
        final FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), oddPred);
        // Move to the end of the list
        while (filtered.hasNext()) {
            filtered.next();
        }
        // Test previous
        assertEquals(19, filtered.previous());
        assertEquals(17, filtered.previous());
        assertEquals(15, filtered.previous());
    }

    @Test
    public void testPreviousNoSuchElementException() {
        final FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), evenPred);
        // Ensure NoSuchElementException is thrown when no previous element exists
        assertThrows(NoSuchElementException.class, () -> filtered.previous());
    }
}
