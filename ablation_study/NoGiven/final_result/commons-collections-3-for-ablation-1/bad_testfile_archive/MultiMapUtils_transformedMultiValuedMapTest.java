
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultiMapUtils_transformedMultiValuedMapTest {

    @Test
    void testTransformedMultiValuedMap() {
        // Given
        MultiValuedMap<String, String> originalMap = MultiMapUtils.newListValuedHashMap();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

        Transformer<String, String> keyTransformer = input -> "transformed_" + input;
        Transformer<String, String> valueTransformer = input -> "transformed_" + input;

        // When
        MultiValuedMap<String, String> transformedMap = MultiMapUtils.transformedMultiValuedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertFalse(MultiMapUtils.isEmpty(transformedMap));
        assertTrue(transformedMap.containsKey("transformed_key1"));
        assertTrue(transformedMap.containsKey("transformed_key2"));
        assertEquals("transformed_value1", transformedMap.get("transformed_key1").iterator().next());
        assertEquals("transformed_value2", transformedMap.get("transformed_key2").iterator().next());
    }
}
