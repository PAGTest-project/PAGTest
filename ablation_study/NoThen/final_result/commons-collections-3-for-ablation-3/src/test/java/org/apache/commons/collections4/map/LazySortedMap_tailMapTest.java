
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
        originalMap.put(1, "one");
        originalMap.put(2, "two");
        originalMap.put(3, "three");
        Factory<String> factory = () -> "default";
        LazySortedMap<Integer, String> lazySortedMap = LazySortedMap.lazySortedMap(originalMap, factory);

        // When
        SortedMap<Integer, String> resultMap = lazySortedMap.tailMap(2);

        // Then
        assertNotNull(resultMap);
        assertEquals(2, resultMap.size());
        assertEquals("two", resultMap.get(2));
        assertEquals("three", resultMap.get(3));
    }
}
