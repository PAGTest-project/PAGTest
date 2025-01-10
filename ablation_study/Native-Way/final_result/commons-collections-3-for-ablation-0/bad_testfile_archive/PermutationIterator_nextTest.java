
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PermutationIterator_nextTest {

    private PermutationIterator<Integer> iterator;

    @BeforeEach
    public void setUp() {
        List<Integer> collection = Arrays.asList(1, 2, 3);
        iterator = new PermutationIterator<>(collection);
    }

    @Test
    public void testNextWithValidPermutation() {
        List<Integer> expectedPermutation = Arrays.asList(1, 2, 3);
        List<Integer> actualPermutation = iterator.next();
        assertEquals(expectedPermutation, actualPermutation);
    }

    @Test
    public void testNextWithNoMorePermutations() {
        // Consume all permutations
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    public void testNextWithMultiplePermutations() {
        List<List<Integer>> expectedPermutations = new ArrayList<>();
        expectedPermutations.add(Arrays.asList(1, 2, 3));
        expectedPermutations.add(Arrays.asList(1, 3, 2));
        expectedPermutations.add(Arrays.asList(2, 1, 3));
        expectedPermutations.add(Arrays.asList(2, 3, 1));
        expectedPermutations.add(Arrays.asList(3, 1, 2));
        expectedPermutations.add(Arrays.asList(3, 2, 1));

        for (List<Integer> expected : expectedPermutations) {
            assertTrue(iterator.hasNext());
            assertEquals(expected, iterator.next());
        }
    }

    @Test
    public void testNextWithSingleElementCollection() {
        List<Integer> collection = Arrays.asList(1);
        iterator = new PermutationIterator<>(collection);
        List<Integer> expectedPermutation = Arrays.asList(1);
        assertEquals(expectedPermutation, iterator.next());
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    public void testNextWithEmptyCollection() {
        List<Integer> collection = new ArrayList<>();
        iterator = new PermutationIterator<>(collection);
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }
}
