
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollatingIterator_removeTest {

    private Comparator<Integer> comparator;
    private ArrayList<Integer> evens;
    private ArrayList<Integer> odds;

    @BeforeEach
    public void setUp() throws Exception {
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
    }

    @Test
    public void testRemoveSuccess() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, odds.iterator(), evens.iterator());
        iter.next(); // Move to the first element
        iter.remove(); // Remove the last returned element
        assertEquals(9, odds.size()); // Verify the element was removed from the correct list
    }

    @Test
    public void testRemoveWithoutNext() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, odds.iterator(), evens.iterator());
        assertThrows(IllegalStateException.class, iter::remove); // Ensure remove throws IllegalStateException if next() hasn't been called
    }

    @Test
    public void testRemoveTwice() {
        CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, odds.iterator(), evens.iterator());
        iter.next(); // Move to the first element
        iter.remove(); // Remove the last returned element
        assertThrows(IllegalStateException.class, iter::remove); // Ensure remove throws IllegalStateException if called twice in a row
    }
}
