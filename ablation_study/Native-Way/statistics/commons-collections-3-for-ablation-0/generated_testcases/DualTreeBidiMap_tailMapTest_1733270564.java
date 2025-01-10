
package org.apache.commons.collections4.bidimap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.apache.commons.collections4.comparators.ReverseComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DualTreeBidiMap_tailMapTest {

    private DualTreeBidiMap<String, Integer> dtbm;

    @BeforeEach
    public void setUp() {
        dtbm = new DualTreeBidiMap<>(String.CASE_INSENSITIVE_ORDER, null);
        dtbm.put("one", 1);
        dtbm.put("two", 2);
        dtbm.put("three", 3);
    }

    @Test
    public void testTailMap() {
        SortedMap<String, Integer> tailMap = dtbm.tailMap("two");
        assertNotNull(tailMap);
        assertEquals(2, tailMap.size());
        assertTrue(tailMap.containsKey("two"));
        assertTrue(tailMap.containsKey("three"));
    }

    @Test
    public void testTailMapWithNonExistentKey() {
        SortedMap<String, Integer> tailMap = dtbm.tailMap("four");
        assertNotNull(tailMap);
        assertTrue(tailMap.isEmpty());
    }

    @Test
    public void testTailMapWithFirstKey() {
        SortedMap<String, Integer> tailMap = dtbm.tailMap("one");
        assertNotNull(tailMap);
        assertEquals(3, tailMap.size());
        assertTrue(tailMap.containsKey("one"));
        assertTrue(tailMap.containsKey("two"));
        assertTrue(tailMap.containsKey("three"));
    }

    @Test
    public void testTailMapWithLastKey() {
        SortedMap<String, Integer> tailMap = dtbm.tailMap("three");
        assertNotNull(tailMap);
        assertEquals(1, tailMap.size());
        assertTrue(tailMap.containsKey("three"));
    }

    @Test
    public void testTailMapWithComparator() {
        Comparator<String> reverseComparator = new ReverseComparator<>(String.CASE_INSENSITIVE_ORDER);
        dtbm = new DualTreeBidiMap<>(reverseComparator, null);
        dtbm.put("one", 1);
        dtbm.put("two", 2);
        dtbm.put("three", 3);

        SortedMap<String, Integer> tailMap = dtbm.tailMap("two");
        assertNotNull(tailMap);
        assertEquals(2, tailMap.size());
        assertTrue(tailMap.containsKey("two"));
        assertTrue(tailMap.containsKey("one"));
    }
}
