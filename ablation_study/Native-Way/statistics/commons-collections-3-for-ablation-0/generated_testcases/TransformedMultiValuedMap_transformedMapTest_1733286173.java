
package org.apache.commons.collections4.multimap;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransformedMultiValuedMap_transformedMapTest {

    @Test
    void testTransformedMapWithNonEmptyMap() {
        // Given
        MultiValuedMap<String, String> originalMap = mock(MultiValuedMap.class);
        when(originalMap.isEmpty()).thenReturn(false);

        Transformer<String, String> keyTransformer = mock(Transformer.class);
        Transformer<String, String> valueTransformer = mock(Transformer.class);

        // When
        TransformedMultiValuedMap<String, String> result = TransformedMultiValuedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertNotNull(result);
        verify(originalMap).isEmpty();
        verify(originalMap, times(1)).clear();
        verify(originalMap, times(1)).putAll(any(MultiValuedMap.class));
    }

    @Test
    void testTransformedMapWithEmptyMap() {
        // Given
        MultiValuedMap<String, String> originalMap = mock(MultiValuedMap.class);
        when(originalMap.isEmpty()).thenReturn(true);

        Transformer<String, String> keyTransformer = mock(Transformer.class);
        Transformer<String, String> valueTransformer = mock(Transformer.class);

        // When
        TransformedMultiValuedMap<String, String> result = TransformedMultiValuedMap.transformedMap(originalMap, keyTransformer, valueTransformer);

        // Then
        assertNotNull(result);
        verify(originalMap).isEmpty();
        verify(originalMap, never()).clear();
        verify(originalMap, never()).putAll(any(MultiValuedMap.class));
    }
}
