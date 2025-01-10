
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterListIterator_previousTest {

    private ArrayList<Integer> list;
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
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), truePred);
        filtered.next(); // Move to the first element
        assertEquals(Integer.valueOf(0), filtered.previous());
    }

    @Test
    public void testPreviousWithFalsePredicate() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), falsePred);
        assertThrows(NoSuchElementException.class, () -> filtered.previous());
    }

    @Test
    public void testPreviousWithEvenPredicate() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), evenPred);
        filtered.next(); // Move to the first even element
        assertEquals(Integer.valueOf(0), filtered.previous());
    }

    @Test
    public void testPreviousWithOddPredicate() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), oddPred);
        filtered.next(); // Move to the first odd element
        assertEquals(Integer.valueOf(1), filtered.previous());
    }

    @Test
    public void testPreviousWithThreePredicate() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), threePred);
        filtered.next(); // Move to the first multiple of 3
        assertEquals(Integer.valueOf(0), filtered.previous());
    }

    @Test
    public void testPreviousWithFourPredicate() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), fourPred);
        filtered.next(); // Move to the first multiple of 4
        assertEquals(Integer.valueOf(0), filtered.previous());
    }
}
