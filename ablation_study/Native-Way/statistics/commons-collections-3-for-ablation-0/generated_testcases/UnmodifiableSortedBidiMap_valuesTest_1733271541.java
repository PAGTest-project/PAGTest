
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBidiMap_valuesTest {

    @Test
    public void testValues() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "one");
        originalMap.put(2, "two");
        SortedBidiMap<Integer, String> bidiMap = new DualTreeBidiMap<>(originalMap);
        bidiMap = new UnmodifiableSortedBidiMap<>(bidiMap);

        // When
        Set<String> values = bidiMap.values();

        // Then
        assertTrue(values instanceof UnmodifiableSet);
        assertEquals(2, values.size());
        assertTrue(values.contains("one"));
        assertTrue(values.contains("two"));
    }
}
