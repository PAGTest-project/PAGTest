
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.map.UnmodifiableSortedMap;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBidiMap_subMapTest {

    @Test
    public void testSubMap() {
        SortedBidiMap<Integer, String> originalMap = new TreeBidiMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");

        UnmodifiableSortedBidiMap<Integer, String> unmodifiableMap = UnmodifiableSortedBidiMap.unmodifiableSortedBidiMap(originalMap);

        SortedMap<Integer, String> subMap = unmodifiableMap.subMap(1, 3);

        assertTrue(subMap instanceof UnmodifiableSortedMap);
        assertEquals(2, subMap.size());
        assertEquals("One", subMap.get(1));
        assertEquals("Two", subMap.get(2));
    }
}
