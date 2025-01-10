
package org.apache.commons.collections4.set;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedSortedSet_transformedSortedSetTest {

    @Test
    public void testTransformedSortedSetWithNonEmptySet() {
        SortedSet<String> originalSet = new TreeSet<>();
        originalSet.add("a");
        originalSet.add("b");
        originalSet.add("c");

        Transformer<String, String> transformer = s -> s.toUpperCase();

        TransformedSortedSet<String> transformedSet = TransformedSortedSet.transformedSortedSet(originalSet, transformer);

        assertEquals(3, originalSet.size()); // Fix: originalSet should still have 3 elements
        assertEquals(3, transformedSet.size());
        assertTrue(transformedSet.contains("A"));
        assertTrue(transformedSet.contains("B"));
        assertTrue(transformedSet.contains("C"));
    }

    @Test
    public void testTransformedSortedSetWithEmptySet() {
        SortedSet<String> originalSet = new TreeSet<>();

        Transformer<String, String> transformer = s -> s.toUpperCase();

        TransformedSortedSet<String> transformedSet = TransformedSortedSet.transformedSortedSet(originalSet, transformer);

        assertEquals(0, originalSet.size());
        assertEquals(0, transformedSet.size());
    }
}
