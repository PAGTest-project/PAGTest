
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Factory;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LazySortedMap_tailMapTest {

    @Test
    public void testTailMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");
        Factory<String> factory = () -> "Default";
        LazySortedMap<Integer, String> lazySortedMap = LazySortedMap.lazySortedMap(originalMap, factory);

        // When
        SortedMap<Integer, String> resultMap = lazySortedMap.tailMap(2);

        // Then
        assertNotNull(resultMap);
        assertEquals(2, resultMap.size());
        assertEquals("Two", resultMap.get(2));
        assertEquals("Three", resultMap.get(3));
    }
}
