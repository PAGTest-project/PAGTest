
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedSortedMap_transformedSortedMapTest {

    @Test
    public void testTransformedSortedMap_EmptyMap() {
        SortedMap<String, Integer> map = new TreeMap<>();
        Transformer<String, String> keyTransformer = input -> input;
        Transformer<Integer, Integer> valueTransformer = input -> input;

        TransformedSortedMap<String, Integer> result = TransformedSortedMap.transformedSortedMap(map, keyTransformer, valueTransformer);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testTransformedSortedMap_NonEmptyMap() {
        SortedMap<String, Integer> map = new TreeMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        Transformer<String, String> keyTransformer = input -> input.toUpperCase();
        Transformer<Integer, Integer> valueTransformer = input -> input * 2;

        TransformedSortedMap<String, Integer> result = TransformedSortedMap.transformedSortedMap(map, keyTransformer, valueTransformer);

        assertEquals(2, result.size());
        assertEquals(2, result.get("KEY1"));
        assertEquals(4, result.get("KEY2"));
    }
}
