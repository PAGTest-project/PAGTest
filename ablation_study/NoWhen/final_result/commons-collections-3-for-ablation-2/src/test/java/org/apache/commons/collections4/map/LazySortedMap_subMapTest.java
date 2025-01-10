
package org.apache.commons.collections4.map;

import static org.apache.commons.collections4.map.LazySortedMap.lazySortedMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LazySortedMap_subMapTest {

    private static final Factory<Integer> oneFactory = FactoryUtils.constantFactory(1);
    private SortedMap<String, Integer> originalMap;
    private LazySortedMap<String, Integer> lazySortedMap;

    @BeforeEach
    public void setUp() {
        originalMap = new TreeMap<>();
        originalMap.put("A", 5);
        originalMap.put("B", 6);
        originalMap.put("C", 7);
        lazySortedMap = lazySortedMap(originalMap, oneFactory);
    }

    @Test
    public void testSubMap() {
        SortedMap<String, Integer> subMap = lazySortedMap.subMap("A", "C");
        assertInstanceOf(LazySortedMap.class, subMap);
        assertEquals(2, subMap.size());
        assertEquals("A", subMap.firstKey());
        assertEquals("B", subMap.lastKey());
    }

    @Test
    public void testSubMapWithNonExistentKeys() {
        SortedMap<String, Integer> subMap = lazySortedMap.subMap("D", "E");
        assertInstanceOf(LazySortedMap.class, subMap);
        assertEquals(0, subMap.size());
        assertThrows(java.util.NoSuchElementException.class, subMap::firstKey);
        assertThrows(java.util.NoSuchElementException.class, subMap::lastKey);
    }

    @Test
    public void testSubMapWithInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> lazySortedMap.subMap("C", "A"));
    }

    @Test
    public void testSubMapWithSameKeys() {
        SortedMap<String, Integer> subMap = lazySortedMap.subMap("B", "B");
        assertInstanceOf(LazySortedMap.class, subMap);
        assertEquals(0, subMap.size());
        assertThrows(java.util.NoSuchElementException.class, subMap::firstKey);
        assertThrows(java.util.NoSuchElementException.class, subMap::lastKey);
    }
}
