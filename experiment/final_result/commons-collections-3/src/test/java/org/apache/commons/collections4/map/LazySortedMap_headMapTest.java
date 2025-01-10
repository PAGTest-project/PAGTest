
package org.apache.commons.collections4.map;

import static org.apache.commons.collections4.map.LazySortedMap.lazySortedMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LazySortedMap_headMapTest {

    private static final Factory<Integer> oneFactory = FactoryUtils.constantFactory(1);
    private SortedMap<String, Integer> originalMap;
    private LazySortedMap<String, Integer> lazySortedMap;

    @BeforeEach
    public void setUp() {
        originalMap = new TreeMap<>();
        originalMap.put("A", 5);
        originalMap.put("B", 8);
        originalMap.put("C", 10);
        lazySortedMap = lazySortedMap(originalMap, oneFactory);
    }

    @Test
    public void testHeadMap() {
        SortedMap<String, Integer> headMap = lazySortedMap.headMap("C");
        assertInstanceOf(LazySortedMap.class, headMap);
        assertEquals(2, headMap.size());
        assertEquals(Integer.valueOf(5), headMap.get("A"));
        assertEquals(Integer.valueOf(8), headMap.get("B"));
    }
}
