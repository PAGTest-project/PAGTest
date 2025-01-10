
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterListIterator_previousTest {

    private List<Integer> list;
    private Predicate<Integer> truePred;
    private Predicate<Integer> falsePred;
    private Predicate<Integer> evenPred;
    private Predicate<Integer> oddPred;
    private Predicate<Integer> threePred;
    private Predicate<Integer> fourPred;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }

        truePred = x -> true;
        falsePred = x -> false;
        evenPred = x -> x % 2 == 0;
        oddPred = x -> x % 2 != 0;
        threePred = x -> x % 3 == 0;
        fourPred = x -> x % 4 == 0;
    }

    @Test
    public void testPreviousWithTruePredicate() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), truePred);
        iterator.next(); // Move to the first element
        assertEquals(0, iterator.previous());
    }

    @Test
    public void testPreviousWithFalsePredicate() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), falsePred);
        assertThrows(NoSuchElementException.class, iterator::previous);
    }

    @Test
    public void testPreviousWithEvenPredicate() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), evenPred);
        iterator.next(); // Move to the first even element (0)
        assertEquals(0, iterator.previous());
    }

    @Test
    public void testPreviousWithOddPredicate() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), oddPred);
        iterator.next(); // Move to the first odd element (1)
        assertEquals(1, iterator.previous());
    }

    @Test
    public void testPreviousWithThreePredicate() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), threePred);
        iterator.next(); // Move to the first multiple of 3 (0)
        assertEquals(0, iterator.previous());
    }

    @Test
    public void testPreviousWithFourPredicate() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), fourPred);
        iterator.next(); // Move to the first multiple of 4 (0)
        assertEquals(0, iterator.previous());
    }

    @Test
    public void testPreviousNoPreviousObjectSet() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), truePred);
        assertThrows(NoSuchElementException.class, iterator::previous);
    }

    @Test
    public void testPreviousAfterNext() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), truePred);
        iterator.next(); // Move to the first element
        iterator.next(); // Move to the second element
        assertEquals(1, iterator.previous());
    }

    @Test
    public void testPreviousAfterPrevious() {
        FilterListIterator<Integer> iterator = new FilterListIterator<>(list.listIterator(), truePred);
        iterator.next(); // Move to the first element
        iterator.previous(); // Move back to the start
        assertThrows(NoSuchElementException.class, iterator::previous);
    }
}
