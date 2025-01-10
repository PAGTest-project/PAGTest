
package org.apache.commons.collections4;

import org.apache.commons.collections4.map.TransformedSortedMap;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapUtils_transformedSortedMapTest {

    @Test
    public void testTransformedSortedMap() {
        // Given
        SortedMap<String, Integer> originalMap = new TreeMap<>();
        originalMap.put("one", 1);
        originalMap.put("two", 2);

        Transformer<String, String> keyTransformer = new ConstantTransformer<>("key");
        Transformer<Integer, Integer> valueTransformer = new ConstantTransformer<>(0);

        // When
        SortedMap<String, Integer> transformedMap = MapUtils.transformedSortedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertEquals(1, transformedMap.size());
        assertEquals(0, transformedMap.get("key"));
    }
}
