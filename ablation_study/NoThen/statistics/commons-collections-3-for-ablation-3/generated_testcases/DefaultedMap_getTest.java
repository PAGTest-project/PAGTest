
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
        DefaultedMap<String, String> defaultedMap = new DefaultedMap<>(map, mock(Transformer.class));

        // When
        String result = defaultedMap.get("key1");

        // Then
        assertEquals("value1", result);
    }

    @Test
    public void testGet_KeyNotInMapButExistsInMapAsNull() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", null);
        DefaultedMap<String, String> defaultedMap = new DefaultedMap<>(map, mock(Transformer.class));

        // When
        String result = defaultedMap.get("key1");

        // Then
        assertEquals(null, result);
    }

    @Test
    public void testGet_KeyNotInMapAndTransformerReturnsValue() {
        // Given
        Map<String, String> map = new HashMap<>();
        Transformer<String, String> transformer = mock(Transformer.class);
        when(transformer.apply("key1")).thenReturn("default");
        DefaultedMap<String, String> defaultedMap = new DefaultedMap<>(map, transformer);

        // When
        String result = defaultedMap.get("key1");

        // Then
        assertEquals("default", result);
    }
}
