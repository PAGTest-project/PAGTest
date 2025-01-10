
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedSortedMap_tailMapTest {

    @Test
    public void testTailMap() {
        // Given
        SortedMap<Integer, String> originalMap = new TreeMap<>();
        originalMap.put(1, "one");
        originalMap.put(2, "two");
        originalMap.put(3, "three");

        Transformer<Integer, Integer> keyTransformer = TransformerUtils.nopTransformer();
        Transformer<String, String> valueTransformer = TransformerUtils.nopTransformer();

        TransformedSortedMap<Integer, String> transformedMap = new TransformedSortedMap<>(originalMap, keyTransformer, valueTransformer);

        // When
        SortedMap<Integer, String> result = transformedMap.tailMap(2);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.containsKey(2));
        assertTrue(result.containsKey(3));
    }
}
