
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransformedSortedMap_transformingSortedMapTest {

    @Test
    public void testTransformingSortedMap() {
        // Given
        SortedMap<String, Integer> originalMap = new TreeMap<>();
        originalMap.put("one", 1);
        originalMap.put("two", 2);

        Transformer<String, String> keyTransformer = input -> input.toUpperCase();
        Transformer<Integer, Integer> valueTransformer = input -> input * 2;

        // When
        TransformedSortedMap<String, Integer> transformedMap = TransformedSortedMap.transformingSortedMap(originalMap, keyTransformer, valueTransformer);

        // Transform the original map to match the expected transformed map
        SortedMap<String, Integer> expectedMap = new TreeMap<>();
        for (SortedMap.Entry<String, Integer> entry : originalMap.entrySet()) {
            expectedMap.put(keyTransformer.transform(entry.getKey()), valueTransformer.transform(entry.getValue()));
        }

        // Then
        assertNotNull(transformedMap);
        assertEquals(expectedMap.size(), transformedMap.size());
        for (SortedMap.Entry<String, Integer> entry : expectedMap.entrySet()) {
            assertEquals(entry.getValue(), transformedMap.get(entry.getKey()));
        }
    }
}
