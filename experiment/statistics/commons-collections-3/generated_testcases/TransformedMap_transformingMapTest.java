
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.Test;

public class TransformedMap_transformingMapTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testTransformingMap() {
        final Map<String, String> base = new HashMap<>();
        base.put("A", "1");
        base.put("B", "2");
        base.put("C", "3");

        final Transformer<String, String> keyTransformer = TransformerUtils.stringValueTransformer();
        final Transformer<String, Integer> valueTransformer = input -> Integer.valueOf(input);

        final TransformedMap<String, Integer> transformedMap = TransformedMap.transformingMap(
                (Map<String, String>) base,
                keyTransformer,
                valueTransformer);

        assertNotNull(transformedMap);
        assertEquals(3, transformedMap.size());
        assertEquals(Integer.valueOf(1), transformedMap.get("A"));
        assertEquals(Integer.valueOf(2), transformedMap.get("B"));
        assertEquals(Integer.valueOf(3), transformedMap.get("C"));
    }
}
