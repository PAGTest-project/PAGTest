
package org.apache.commons.collections4.multimap;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransformedMultiValuedMap_transformingMapTest {

    @Test
    void testTransformingMap() {
        // Given
        MultiValuedMap<String, Integer> map = mock(MultiValuedMap.class);
        Transformer<String, String> keyTransformer = mock(Transformer.class);
        Transformer<Integer, Integer> valueTransformer = mock(Transformer.class);

        // When
        TransformedMultiValuedMap<String, Integer> result = TransformedMultiValuedMap.transformingMap(map, keyTransformer, valueTransformer);

        // Then
        assertNotNull(result);
        assertEquals(map, result.decorated());
        assertEquals(keyTransformer, result.getKeyTransformer());
        assertEquals(valueTransformer, result.getValueTransformer());
    }
}
