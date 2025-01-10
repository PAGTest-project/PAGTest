
package org.apache.commons.collections4.map;

import static org.apache.commons.collections4.map.LazySortedMap.lazySortedMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LazySortedMap_headMapTest {

    private static final Factory<Integer> oneFactory = FactoryUtils.constantFactory(1);
    private LazySortedMap<String, Integer> lazySortedMap;

    @BeforeEach
    public void setUp() {
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("A", 5);
        sortedMap.put("C", 8);
        lazySortedMap = lazySortedMap(sortedMap, oneFactory);
    }

    @Test
    public void testHeadMap() {
        SortedMap<String, Integer> headMap = lazySortedMap.headMap("C");
        assertInstanceOf(LazySortedMap.class, headMap);
        assertEquals(1, headMap.size());
        assertEquals(Integer.valueOf(5), headMap.get("A"));
    }

    @Test
    public void testHeadMapWithNonExistentKey() {
        SortedMap<String, Integer> headMap = lazySortedMap.headMap("B");
        assertInstanceOf(LazySortedMap.class, headMap);
        assertEquals(1, headMap.size());
        assertEquals(Integer.valueOf(5), headMap.get("A"));
    }

    @Test
    public void testHeadMapWithNullKey() {
        assertThrows(NullPointerException.class, () -> lazySortedMap.headMap(null));
    }
}
