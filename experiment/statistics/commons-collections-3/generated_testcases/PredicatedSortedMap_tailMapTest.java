
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicatedSortedMap_tailMapTest {

    private static final Predicate<Object> testPredicate = TruePredicate.truePredicate();
    private PredicatedSortedMap<String, String> predicatedSortedMap;

    @BeforeEach
    public void setUp() {
        SortedMap<String, String> map = new TreeMap<>();
        predicatedSortedMap = PredicatedSortedMap.predicatedSortedMap(map, testPredicate, testPredicate);
    }

    @Test
    public void testTailMap() {
        predicatedSortedMap.put("A", "a");
        predicatedSortedMap.put("B", "b");
        predicatedSortedMap.put("C", "c");

        SortedMap<String, String> tailMap = predicatedSortedMap.tailMap("B");
        assertNotNull(tailMap);
        assertEquals(2, tailMap.size());
        assertEquals("B", tailMap.firstKey());
        assertEquals("C", tailMap.lastKey());
    }

    @Test
    public void testTailMapWithInvalidKey() {
        predicatedSortedMap.put("A", "a");
        predicatedSortedMap.put("B", "b");
        predicatedSortedMap.put("C", "c");

        assertThrows(NullPointerException.class, () -> predicatedSortedMap.tailMap(null));
    }
}
