
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterListIterator_nextTest {

    private ArrayList<Integer> list;
    private Predicate<Integer> evenPred;
    private Predicate<Integer> threePred;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }

        evenPred = x -> x % 2 == 0;
        threePred = x -> x % 3 == 0;
    }

    @Test
    public void testNextWithValidElement() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), evenPred);
        assertEquals(Integer.valueOf(0), filtered.next());
        assertEquals(Integer.valueOf(2), filtered.next());
    }

    @Test
    public void testNextWithNoValidElement() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), threePred);
        assertEquals(Integer.valueOf(0), filtered.next());
        assertEquals(Integer.valueOf(3), filtered.next());
        assertThrows(NoSuchElementException.class, filtered::next);
    }

    @Test
    public void testNextWithEmptyList() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        FilterListIterator<Integer> filtered = new FilterListIterator<>(emptyList.listIterator(), evenPred);
        assertThrows(NoSuchElementException.class, filtered::next);
    }

    @Test
    public void testNextWithNullPredicate() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), null);
        assertThrows(NullPointerException.class, filtered::next);
    }

    @Test
    public void testNextWithNullIterator() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(null, evenPred);
        assertThrows(NullPointerException.class, filtered::next);
    }
}
