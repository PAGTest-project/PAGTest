
package net.hydromatic.morel.compile;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeSet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Extents_intersectTest {

    @Test
    void testIntersect_EmptyList() {
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList();
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        assertEquals(ImmutableMap.of(), result);
    }

    @Test
    void testIntersect_SingleMap() {
        ImmutableRangeSet<Integer> rangeSet = ImmutableRangeSet.of();
        Map<String, ImmutableRangeSet<Integer>> singleMap = ImmutableMap.of("key", rangeSet);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList(singleMap);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        assertEquals(singleMap, result);
    }

    @Test
    void testIntersect_MultipleMaps() {
        ImmutableRangeSet<Integer> rangeSet1 = ImmutableRangeSet.of();
        ImmutableRangeSet<Integer> rangeSet2 = ImmutableRangeSet.of();
        Map<String, ImmutableRangeSet<Integer>> map1 = ImmutableMap.of("key1", rangeSet1);
        Map<String, ImmutableRangeSet<Integer>> map2 = ImmutableMap.of("key2", rangeSet2);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList(map1, map2);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        assertEquals(2, result.size());
    }
}
