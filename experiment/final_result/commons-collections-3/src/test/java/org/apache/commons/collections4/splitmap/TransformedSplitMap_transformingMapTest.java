
package org.apache.commons.collections4.splitmap;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.LinkedMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransformedSplitMap_transformingMapTest {

    @Test
    public void testTransformingMap() {
        // Given
        Map<String, Integer> map = new LinkedMap<>();
        Transformer<String, String> keyTransformer = input -> input;
        Transformer<Integer, Integer> valueTransformer = input -> input;

        // When
        TransformedSplitMap<String, String, Integer, Integer> result = TransformedSplitMap.transformingMap(map, keyTransformer, valueTransformer);

        // Then
        assertNotNull(result);
    }
}
