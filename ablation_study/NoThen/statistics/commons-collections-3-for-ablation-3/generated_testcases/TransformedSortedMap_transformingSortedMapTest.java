
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
        expectedMap.put("ONE", 2);
        expectedMap.put("TWO", 4);

        // Then
        assertNotNull(transformedMap);
        assertEquals(expectedMap.size(), transformedMap.size());
        assertEquals(expectedMap.get("ONE"), transformedMap.get("ONE"));
        assertEquals(expectedMap.get("TWO"), transformedMap.get("TWO"));
    }
}
