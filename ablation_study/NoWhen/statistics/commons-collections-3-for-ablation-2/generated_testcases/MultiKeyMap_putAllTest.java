
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MultiKeyMap_putAllTest {

    @Test
    void testPutAll() {
        // Given
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();
        Map<MultiKey<? extends String>, String> mapToCopy = new HashMap<>();
        MultiKey<String> key1 = new MultiKey<>("key1", "key2");
        MultiKey<String> key2 = new MultiKey<>("key3", "key4");
        mapToCopy.put(key1, "value1");
        mapToCopy.put(key2, "value2");

        // When
        multiKeyMap.putAll(mapToCopy);

        // Then
        assertTrue(multiKeyMap.containsKey("key1", "key2"));
        assertTrue(multiKeyMap.containsKey("key3", "key4"));
        assertEquals("value1", multiKeyMap.get(key1));
        assertEquals("value2", multiKeyMap.get(key2));
    }

    @Test
    void testPutAllWithNullKey() {
        // Given
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();
        Map<MultiKey<? extends String>, String> mapToCopy = new HashMap<>();
        MultiKey<String> nullKey = null;
        mapToCopy.put(nullKey, "value");

        // When & Then
        assertThrows(NullPointerException.class, () -> multiKeyMap.putAll(mapToCopy));
    }
}
