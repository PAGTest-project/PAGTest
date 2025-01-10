
package net.hydromatic.morel.compile;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeSet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Extents_unionTest {

    @Test
    public void testUnionWithEmptyList() {
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList();
        Map<String, ImmutableRangeSet<Integer>> result = Extents.union(rangeSetMaps);
        assertEquals(ImmutableMap.of("/", ImmutableRangeSet.of()), result);
    }

    @Test
    public void testUnionWithSingleMap() {
        ImmutableRangeSet<Integer> rangeSet = ImmutableRangeSet.of();
        Map<String, ImmutableRangeSet<Integer>> singleMap = ImmutableMap.of("path", rangeSet);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList(singleMap);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.union(rangeSetMaps);
        assertEquals(singleMap, result);
    }

    @Test
    public void testUnionWithMultipleMaps() {
        ImmutableRangeSet<Integer> rangeSet1 = ImmutableRangeSet.of();
        ImmutableRangeSet<Integer> rangeSet2 = ImmutableRangeSet.of();
        Map<String, ImmutableRangeSet<Integer>> map1 = ImmutableMap.of("path1", rangeSet1);
        Map<String, ImmutableRangeSet<Integer>> map2 = ImmutableMap.of("path2", rangeSet2);
        List<Map<String, ImmutableRangeSet<Integer>>> rangeSetMaps = Arrays.asList(map1, map2);
        Map<String, ImmutableRangeSet<Integer>> result = Extents.union(rangeSetMaps);
        assertEquals(2, result.size());
    }
}
