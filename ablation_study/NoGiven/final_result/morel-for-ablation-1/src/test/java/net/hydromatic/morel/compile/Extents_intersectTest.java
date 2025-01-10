
package net.hydromatic.morel.compile;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.Range;
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
        ImmutableRangeSet<Integer> rangeSet = ImmutableRangeSet.<Integer>builder().add(Range.closed(1, 10)).build();
        Map<String, ImmutableRangeSet<Integer>> singleMap = ImmutableMap.of("key1", rangeSet);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList(singleMap);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        assertEquals(singleMap, result);
    }

    @Test
    void testIntersect_MultipleMaps() {
        ImmutableRangeSet<Integer> rangeSet1 = ImmutableRangeSet.<Integer>builder().add(Range.closed(1, 10)).build();
        ImmutableRangeSet<Integer> rangeSet2 = ImmutableRangeSet.<Integer>builder().add(Range.closed(5, 15)).build();
        Map<String, ImmutableRangeSet<Integer>> map1 = ImmutableMap.of("key1", rangeSet1);
        Map<String, ImmutableRangeSet<Integer>> map2 = ImmutableMap.of("key1", rangeSet2);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList(map1, map2);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.intersect(rangeSetMaps);
        ImmutableRangeSet<Integer> expectedRangeSet = ImmutableRangeSet.<Integer>builder().add(Range.closed(5, 10)).build();
        assertEquals(ImmutableMap.of("key1", expectedRangeSet), result);
    }
}
