
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DefaultedMap_getTest {

    @Test
    public void testGet_KeyExistsInMap() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        DefaultedMap<String, String> defaultedMap = new DefaultedMap<>(map, (Transformer<String, String>) mock(Transformer.class));

        // When
        String result = defaultedMap.get("key1");

        // Then
        assertEquals("value1", result);
    }

    @Test
    public void testGet_KeyNotInMapButExistsInMapAfterTransformer() {
        // Given
        Map<String, String> map = new HashMap<>();
        Transformer<String, String> transformer = mock(Transformer.class);
        when(transformer.apply("key2")).thenReturn("value2");
        DefaultedMap<String, String> defaultedMap = new DefaultedMap<>(map, transformer);

        // When
        String result = defaultedMap.get("key2");

        // Then
        assertEquals("value2", result);
    }

    @Test
    public void testGet_KeyNotInMapAndNotExistsInMapAfterTransformer() {
        // Given
        Map<String, String> map = new HashMap<>();
        Transformer<String, String> transformer = mock(Transformer.class);
        when(transformer.apply("key3")).thenReturn(null);
        DefaultedMap<String, String> defaultedMap = new DefaultedMap<>(map, transformer);

        // When
        String result = defaultedMap.get("key3");

        // Then
        assertEquals(null, result);
    }
}
