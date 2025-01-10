
package org.apache.commons.collections4;

import org.apache.commons.collections4.map.TransformedMap;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapUtils_transformedMapTest {

    @Test
    public void testTransformedMap() {
        // Given
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("key1", 1);
        originalMap.put("key2", 2);

        Transformer<String, String> keyTransformer = new ConstantTransformer<>("transformedKey");
        Transformer<Integer, Integer> valueTransformer = new ConstantTransformer<>(99);

        // When
        IterableMap<String, Integer> transformedMap = MapUtils.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertEquals(1, transformedMap.size());
        assertEquals(99, transformedMap.get("transformedKey"));
    }
}
