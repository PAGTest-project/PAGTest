
package net.hydromatic.morel.compile;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeSet;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Extents_intersectTest {

    @Test
    void testIntersectWithEmptyList() {
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Collections.emptyList();
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        assertEquals(ImmutableMap.of(), result);
    }

    @Test
    void testIntersectWithSingleMap() {
        ImmutableRangeSet<Integer> rangeSet = ImmutableRangeSet.of();
        Map<String, ImmutableRangeSet<Integer>> singleMap = ImmutableMap.of("key", rangeSet);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Collections.singletonList(singleMap);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        assertEquals(singleMap, result);
    }

    @Test
    void testIntersectWithMultipleMaps() {
        ImmutableRangeSet<Integer> rangeSet1 = ImmutableRangeSet.of();
        ImmutableRangeSet<Integer> rangeSet2 = ImmutableRangeSet.of();
        Map<String, ImmutableRangeSet<Integer>> map1 = ImmutableMap.of("key1", rangeSet1);
        Map<String, ImmutableRangeSet<Integer>> map2 = ImmutableMap.of("key2", rangeSet2);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = List.of(map1, map2);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        assertEquals(ImmutableMap.of(), result);
    }
}
