
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicatedSortedMap_headMapTest {

    private static final Predicate<Object> testPredicate = TruePredicate.truePredicate();
    private PredicatedSortedMap<String, String> predicatedSortedMap;

    @BeforeEach
    public void setUp() {
        SortedMap<String, String> baseMap = new TreeMap<>();
        baseMap.put("A", "a");
        baseMap.put("B", "b");
        baseMap.put("C", "c");
        predicatedSortedMap = PredicatedSortedMap.predicatedSortedMap(baseMap, testPredicate, testPredicate);
    }

    @Test
    public void testHeadMap() {
        SortedMap<String, String> headMap = predicatedSortedMap.headMap("B");
        assertNotNull(headMap);
        assertEquals(1, headMap.size());
        assertTrue(headMap.containsKey("A"));
        assertEquals("a", headMap.get("A"));
    }
}
