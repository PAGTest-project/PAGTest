
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

    private PermutationIterator<Character> iterator;

    @BeforeEach
    public void setUp() {
        List<Character> collection = Arrays.asList('A', 'B', 'C');
        iterator = new PermutationIterator<>(collection);
    }

    @Test
    public void testNextWithValidPermutation() {
        assertTrue(iterator.hasNext());
        List<Character> firstPermutation = iterator.next();
        assertEquals(Arrays.asList('A', 'B', 'C'), firstPermutation);
    }

    @Test
    public void testNextThrowsNoSuchElementException() {
        // Consume all permutations
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }
}
