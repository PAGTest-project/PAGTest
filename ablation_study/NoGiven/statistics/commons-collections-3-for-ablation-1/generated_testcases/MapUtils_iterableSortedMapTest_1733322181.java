
package org.apache.commons.collections4;

import org.apache.commons.collections4.map.AbstractSortedMapDecorator;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MapUtils_iterableSortedMapTest {

    @Test
    void testIterableSortedMap_WithNonIterableSortedMap() {
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("a", 1);
        sortedMap.put("b", 2);

        IterableSortedMap<String, Integer> result = MapUtils.iterableSortedMap(sortedMap);

        assertNotNull(result);
        assertTrue(result instanceof AbstractSortedMapDecorator);
        assertEquals(sortedMap, result.decorated());
    }

    @Test
    void testIterableSortedMap_WithIterableSortedMap() {
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("a", 1);
        sortedMap.put("b", 2);

        IterableSortedMap<String, Integer> iterableSortedMap = new AbstractSortedMapDecorator<>(sortedMap) {};

        IterableSortedMap<String, Integer> result = MapUtils.iterableSortedMap(iterableSortedMap);

        assertNotNull(result);
        assertSame(iterableSortedMap, result);
    }

    @Test
    void testIterableSortedMap_WithNullSortedMap() {
        assertThrows(NullPointerException.class, () -> {
            MapUtils.iterableSortedMap(null);
        });
    }
}
