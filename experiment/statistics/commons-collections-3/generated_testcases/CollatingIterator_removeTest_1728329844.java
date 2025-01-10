
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
    private CollatingIterator<Integer> collatingIterator;

    @BeforeEach
    public void setUp() {
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
        collatingIterator = new CollatingIterator<>(comparator, evens.iterator(), odds.iterator());
    }

    @Test
    public void testRemoveSuccess() {
        // Given
        collatingIterator.next(); // Set lastReturned to 0

        // When
        collatingIterator.remove();

        // Then
        assertEquals(1, evens.size()); // Ensure the first element was removed
    }

    @Test
    public void testRemoveWithoutNext() {
        // Given
        // No next() call, lastReturned is still -1

        // When & Then
        assertThrows(IllegalStateException.class, () -> {
            collatingIterator.remove();
        });
    }
}
