
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

public class FilterListIterator_nextTest {

    private ArrayList<Integer> list;
    private ArrayList<Integer> fours;
    private Predicate<Integer> fourPred;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        fours = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
            if (i % 4 == 0) {
                fours.add(Integer.valueOf(i));
            }
        }

        fourPred = x -> x % 4 == 0;
    }

    @Test
    public void testNextWithValidElement() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), fourPred);
        assertTrue(filtered.hasNext());
        assertEquals(fours.get(0), filtered.next());
    }

    @Test
    public void testNextWithNoValidElement() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), x -> x % 100 == 0);
        assertThrows(NoSuchElementException.class, () -> {
            while (filtered.hasNext()) {
                filtered.next();
            }
            filtered.next(); // This should throw NoSuchElementException
        });
    }

    @Test
    public void testNextWithMultipleValidElements() {
        FilterListIterator<Integer> filtered = new FilterListIterator<>(list.listIterator(), fourPred);
        ListIterator<Integer> expected = fours.listIterator();
        while (expected.hasNext()) {
            assertEquals(expected.next(), filtered.next());
        }
    }
}
