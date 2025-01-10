
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PermutationIterator_nextTest {

    private PermutationIterator<Character> permutationIterator;

    @BeforeEach
    public void setUp() {
        permutationIterator = new PermutationIterator<>(Arrays.asList('A', 'B', 'C'));
    }

    @Test
    public void testNextPermutationSuccess() {
        List<Character> expectedPermutation = Arrays.asList('A', 'B', 'C');
        assertEquals(expectedPermutation, permutationIterator.next());
    }

    @Test
    public void testNextPermutationNoSuchElementException() {
        // Consume all permutations
        while (permutationIterator.hasNext()) {
            permutationIterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> {
            permutationIterator.next();
        });
    }

    @Test
    public void testNextPermutationMultipleCalls() {
        List<Character> expectedPermutation1 = Arrays.asList('A', 'B', 'C');
        List<Character> expectedPermutation2 = Arrays.asList('A', 'C', 'B');
        assertEquals(expectedPermutation1, permutationIterator.next());
        assertEquals(expectedPermutation2, permutationIterator.next());
    }

    @Test
    public void testNextPermutationHasNextValidation() {
        assertTrue(permutationIterator.hasNext());
        permutationIterator.next();
        assertTrue(permutationIterator.hasNext());
    }
}
