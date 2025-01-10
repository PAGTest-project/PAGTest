
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBidiMap_entrySetTest {

    @Test
    public void testEntrySet() {
        // Given
        SortedMap<String, String> originalMap = new TreeMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        org.apache.commons.collections4.SortedBidiMap<String, String> unmodifiableMap = UnmodifiableSortedBidiMap.unmodifiableSortedBidiMap(new DualTreeBidiMap<>(originalMap));

        // When
        Set<Map.Entry<String, String>> entrySet = unmodifiableMap.entrySet();

        // Then
        assertTrue(entrySet instanceof UnmodifiableEntrySet);
    }
}
