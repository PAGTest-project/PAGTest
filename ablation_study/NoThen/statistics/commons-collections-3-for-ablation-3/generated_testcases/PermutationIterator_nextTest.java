
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PermutationIterator_nextTest {

    private PermutationIterator<Character> permutationIterator;

    @BeforeEach
    public void setUp() {
        List<Character> collection = Arrays.asList('A', 'B', 'C');
        permutationIterator = new PermutationIterator<>(collection);
    }

    @Test
    public void testNextPermutation() {
        assertTrue(permutationIterator.hasNext());

        List<Character> firstPermutation = permutationIterator.next();
        assertEquals(Arrays.asList('A', 'B', 'C'), firstPermutation);

        assertTrue(permutationIterator.hasNext());

        List<Character> secondPermutation = permutationIterator.next();
        assertEquals(Arrays.asList('A', 'C', 'B'), secondPermutation);

        assertTrue(permutationIterator.hasNext());

        List<Character> thirdPermutation = permutationIterator.next();
        assertEquals(Arrays.asList('C', 'A', 'B'), thirdPermutation);

        assertTrue(permutationIterator.hasNext());

        List<Character> fourthPermutation = permutationIterator.next();
        assertEquals(Arrays.asList('C', 'B', 'A'), fourthPermutation);

        assertTrue(permutationIterator.hasNext());

        List<Character> fifthPermutation = permutationIterator.next();
        assertEquals(Arrays.asList('B', 'C', 'A'), fifthPermutation);

        assertTrue(permutationIterator.hasNext());

        List<Character> sixthPermutation = permutationIterator.next();
        assertEquals(Arrays.asList('B', 'A', 'C'), sixthPermutation);

        assertFalse(permutationIterator.hasNext());
    }

    @Test
    public void testNextPermutationThrowsNoSuchElementException() {
        List<Character> collection = new ArrayList<>();
        PermutationIterator<Character> emptyIterator = new PermutationIterator<>(collection);

        assertTrue(emptyIterator.hasNext());
        assertEquals(0, emptyIterator.next().size());

        assertThrows(NoSuchElementException.class, () -> {
            emptyIterator.next();
        });
    }

    @Test
    public void testRemoveNotSupported() {
        assertThrows(UnsupportedOperationException.class, () -> {
            permutationIterator.remove();
        });
    }
}
