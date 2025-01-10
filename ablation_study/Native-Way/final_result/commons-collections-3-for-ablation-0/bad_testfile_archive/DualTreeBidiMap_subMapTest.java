
package org.apache.commons.collections4.bidimap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DualTreeBidiMap_subMapTest {

    private DualTreeBidiMap<String, Integer> dualTreeBidiMap;

    @BeforeEach
    public void setUp() {
        dualTreeBidiMap = new DualTreeBidiMap<>(String.CASE_INSENSITIVE_ORDER, null);
        dualTreeBidiMap.put("one", 1);
        dualTreeBidiMap.put("two", 2);
        dualTreeBidiMap.put("three", 3);
    }

    @Test
    public void testSubMap() {
        SortedMap<String, Integer> subMap = dualTreeBidiMap.subMap("one", "three");
        assertNotNull(subMap);
        assertEquals(2, subMap.size());
        assertTrue(subMap.containsKey("one"));
        assertTrue(subMap.containsKey("two"));
    }

    @Test
    public void testSubMapEmpty() {
        SortedMap<String, Integer> subMap = dualTreeBidiMap.subMap("four", "five");
        assertNotNull(subMap);
        assertTrue(subMap.isEmpty());
    }

    @Test
    public void testSubMapSingleEntry() {
        SortedMap<String, Integer> subMap = dualTreeBidiMap.subMap("one", "two");
        assertNotNull(subMap);
        assertEquals(1, subMap.size());
        assertTrue(subMap.containsKey("one"));
    }

    @Test
    public void testSubMapWithComparator() {
        Comparator<String> reverseComparator = Comparator.reverseOrder();
        DualTreeBidiMap<String, Integer> reverseBidiMap = new DualTreeBidiMap<>(reverseComparator, null);
        reverseBidiMap.put("one", 1);
        reverseBidiMap.put("two", 2);
        reverseBidiMap.put("three", 3);

        SortedMap<String, Integer> subMap = reverseBidiMap.subMap("two", "one");
        assertNotNull(subMap);
        assertEquals(1, subMap.size());
        assertTrue(subMap.containsKey("two"));
    }
}
