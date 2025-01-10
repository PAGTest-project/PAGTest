
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransformedMap_putTest {

    @Test
    public void testPutWithTransformers() {
        // Given
        Transformer<String, String> keyTransformer = mock(Transformer.class);
        Transformer<String, String> valueTransformer = mock(Transformer.class);
        Map<String, String> originalMap = new HashMap<>();
        TransformedMap<String, String> transformedMap = new TransformedMap<>(originalMap, keyTransformer, valueTransformer);

        String originalKey = "key";
        String originalValue = "value";
        String transformedKey = "transformedKey";
        String transformedValue = "transformedValue";

        when(keyTransformer.apply(originalKey)).thenReturn(transformedKey);
        when(valueTransformer.apply(originalValue)).thenReturn(transformedValue);

        // When
        transformedMap.put(originalKey, originalValue);

        // Then
        assertEquals(transformedValue, originalMap.get(transformedKey));
        verify(keyTransformer).apply(originalKey);
        verify(valueTransformer).apply(originalValue);
    }
}
