
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultiMapUtils_transformedMultiValuedMapTest {

    @Test
    void testTransformedMultiValuedMap() {
        // Given
        MultiValuedMap<String, Integer> originalMap = MultiMapUtils.newListValuedHashMap();
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
        assertTrue(transformedMap.containsKey("key1"));
        assertTrue(transformedMap.containsKey("key2"));
        assertEquals(1, transformedMap.get("key1").iterator().next());
        assertEquals(2, transformedMap.get("key2").iterator().next());
    }
}
