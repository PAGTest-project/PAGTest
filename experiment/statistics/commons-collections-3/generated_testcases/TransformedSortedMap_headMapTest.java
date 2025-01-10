
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedSortedMap_headMapTest {

    @Test
    public void testHeadMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "One");
        originalMap.put(2, "Two");
        originalMap.put(3, "Three");

        Transformer<Integer, Integer> keyTransformer = NOPTransformer.nopTransformer();
        Transformer<String, String> valueTransformer = NOPTransformer.nopTransformer();

        TransformedSortedMap<Integer, String> transformedMap = new TransformedSortedMap<>(originalMap, keyTransformer, valueTransformer);

        // When
        SortedMap<Integer, String> resultMap = transformedMap.headMap(3);

        // Then
        assertEquals(2, resultMap.size());
        assertTrue(resultMap.containsKey(1));
        assertTrue(resultMap.containsKey(2));
    }
}
