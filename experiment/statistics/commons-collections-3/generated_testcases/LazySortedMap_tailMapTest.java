
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

public class LazySortedMap_tailMapTest {

    private static final Factory<Integer> oneFactory = FactoryUtils.constantFactory(1);
    private LazySortedMap<String, Integer> lazySortedMap;

    @BeforeEach
    public void setUp() {
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("A", 5);
        sortedMap.put("B", 7);
        sortedMap.put("C", 8);
        lazySortedMap = lazySortedMap(sortedMap, oneFactory);
    }

    @Test
    public void testTailMap() {
        SortedMap<String, Integer> tailMap = lazySortedMap.tailMap("B");
        assertInstanceOf(LazySortedMap.class, tailMap);
        assertEquals(2, tailMap.size());
        assertEquals(Integer.valueOf(7), tailMap.get("B"));
        assertEquals(Integer.valueOf(8), tailMap.get("C"));
    }
}
