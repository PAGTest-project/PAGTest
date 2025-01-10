
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableOrderedBidiMap_entrySetTest {

    @Test
    public void testEntrySet() {
        // Given
        OrderedBidiMap<String, Integer> originalMap = new DualHashBidiMap<>();
        originalMap.put("one", 1);
        UnmodifiableOrderedBidiMap<String, Integer> unmodifiableMap = UnmodifiableOrderedBidiMap.unmodifiableOrderedBidiMap(originalMap);

        // When
        Set<Map.Entry<String, Integer>> entrySet = unmodifiableMap.entrySet();

        // Then
        assertTrue(entrySet instanceof UnmodifiableEntrySet);
    }
}
