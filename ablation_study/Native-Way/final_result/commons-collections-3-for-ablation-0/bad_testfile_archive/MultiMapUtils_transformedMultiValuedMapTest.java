
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

class MultiMapUtils_transformedMultiValuedMapTest {

    @Test
    void testTransformedMultiValuedMap() {
        // Given
        MultiValuedMap<String, Integer> originalMap = new ArrayListValuedHashMap<>();
        originalMap.put("key1", 1);
        originalMap.put("key2", 2);

        Transformer<String, String> keyTransformer = mock(Transformer.class);
        when(keyTransformer.transform("key1")).thenReturn("transformedKey1");
        when(keyTransformer.transform("key2")).thenReturn("transformedKey2");

        Transformer<Integer, Integer> valueTransformer = mock(Transformer.class);
        when(valueTransformer.transform(1)).thenReturn(10);
        when(valueTransformer.transform(2)).thenReturn(20);

        // When
        MultiValuedMap<String, Integer> transformedMap = MultiMapUtils.transformedMultiValuedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertTrue(transformedMap.get("transformedKey1").contains(10));
        assertTrue(transformedMap.get("transformedKey2").contains(20));
    }
}
