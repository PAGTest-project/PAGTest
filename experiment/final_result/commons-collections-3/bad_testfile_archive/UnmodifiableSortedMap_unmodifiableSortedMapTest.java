
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

public class UnmodifiableSortedMap_unmodifiableSortedMapTest {

    @Test
    public void testUnmodifiableSortedMap_WithUnmodifiableMap() {
        SortedMap<String, String> originalMap = new TreeMap<>();
        originalMap.put("key1", "value1");
        SortedMap<String, String> unmodifiableMap = new UnmodifiableSortedMap<>(originalMap);

        SortedMap<String, String> result = UnmodifiableSortedMap.unmodifiableSortedMap(unmodifiableMap);

        assertSame(unmodifiableMap, result);
    }

    @Test
    public void testUnmodifiableSortedMap_WithModifiableMap() {
        SortedMap<String, String> originalMap = new TreeMap<>();
        originalMap.put("key1", "value1");

        SortedMap<String, String> result = UnmodifiableSortedMap.unmodifiableSortedMap(originalMap);

        assertTrue(result instanceof Unmodifiable);
    }
}
