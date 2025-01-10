
package org.apache.commons.collections4.bidimap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.TreeMap;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.apache.commons.collections4.comparators.ReverseComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DualTreeBidiMap_headMapTest {

    private DualTreeBidiMap<String, Integer> dtbm;

    @BeforeEach
    public void setUp() {
        dtbm = new DualTreeBidiMap<>(String.CASE_INSENSITIVE_ORDER, null);
        dtbm.put("one", 1);
        dtbm.put("two", 2);
        dtbm.put("three", 3);
    }

    @Test
    public void testHeadMap() {
        SortedMap<String, Integer> headMap = dtbm.headMap("three");
        assertNotNull(headMap);
        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey("one"));
        assertTrue(headMap.containsKey("two"));
    }

    @Test
    public void testHeadMapBoundary() {
        SortedMap<String, Integer> headMap = dtbm.headMap("one");
        assertNotNull(headMap);
        assertEquals(0, headMap.size());
    }

    @Test
    public void testHeadMapWithFirstKey() {
        String firstKey = dtbm.firstKey();
        SortedMap<String, Integer> headMap = dtbm.headMap(firstKey);
        assertNotNull(headMap);
        assertEquals(0, headMap.size());
    }

    @Test
    public void testHeadMapWithLastKey() {
        String lastKey = dtbm.lastKey();
        SortedMap<String, Integer> headMap = dtbm.headMap(lastKey);
        assertNotNull(headMap);
        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey("one"));
        assertTrue(headMap.containsKey("two"));
    }

    @Test
    public void testHeadMapWithNextKey() {
        String nextKey = dtbm.nextKey("one");
        SortedMap<String, Integer> headMap = dtbm.headMap(nextKey);
        assertNotNull(headMap);
        assertEquals(1, headMap.size());
        assertTrue(headMap.containsKey("one"));
    }

    @Test
    public void testHeadMapWithPreviousKey() {
        String previousKey = dtbm.previousKey("three");
        SortedMap<String, Integer> headMap = dtbm.headMap(previousKey);
        assertNotNull(headMap);
        assertEquals(1, headMap.size());
        assertTrue(headMap.containsKey("two"));
    }

    @Test
    public void testHeadMapWithInverseBidiMap() {
        SortedBidiMap<Integer, String> inverseBidiMap = dtbm.inverseBidiMap();
        SortedMap<Integer, String> headMap = inverseBidiMap.headMap(3);
        assertNotNull(headMap);
        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey(1));
        assertTrue(headMap.containsKey(2));
    }
}
