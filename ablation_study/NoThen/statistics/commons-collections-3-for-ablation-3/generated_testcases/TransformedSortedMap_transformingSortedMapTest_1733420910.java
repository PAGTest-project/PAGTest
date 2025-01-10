
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

        // Then
        assertNotNull(transformedMap);
        assertEquals(2, transformedMap.size());
        assertEquals(2, transformedMap.get("ONE"));
        assertEquals(4, transformedMap.get("TWO"));
    }
}
