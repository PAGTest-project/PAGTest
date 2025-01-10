
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransformedMap_putAllTest {

    @Test
    public void testPutAll() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

        Transformer<String, String> keyTransformer = mock(Transformer.class);
        Transformer<String, String> valueTransformer = mock(Transformer.class);

        when(keyTransformer.apply("key1")).thenReturn("transformedKey1");
        when(keyTransformer.apply("key2")).thenReturn("transformedKey2");
        when(valueTransformer.apply("value1")).thenReturn("transformedValue1");
        when(valueTransformer.apply("value2")).thenReturn("transformedValue2");

        TransformedMap<String, String> transformedMap = new TransformedMap<>(new HashMap<>(), keyTransformer, valueTransformer);

        // When
        transformedMap.putAll(originalMap);

        // Then
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("transformedKey1", "transformedValue1");
        expectedMap.put("transformedKey2", "transformedValue2");

        assertEquals(expectedMap, transformedMap.decorated());
    }
}
