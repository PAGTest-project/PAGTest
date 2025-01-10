
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.set.UnmodifiableSet;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.bidimap.DualTreeBidiMap;
import org.apache.commons.collections4.bidimap.UnmodifiableSortedBidiMap;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBidiMap_keySetTest {

    @Test
    public void testKeySet() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "one");
        originalMap.put(2, "two");
        SortedBidiMap<Integer, String> bidiMap = new DualTreeBidiMap<>(originalMap);
        bidiMap = new UnmodifiableSortedBidiMap<>(bidiMap);

        // When
        Set<Integer> keySet = bidiMap.keySet();

        // Then
        assertTrue(keySet instanceof UnmodifiableSet);
        assertTrue(keySet.contains(1));
        assertTrue(keySet.contains(2));
    }
}
