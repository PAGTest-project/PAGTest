
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultiKeyMap_putAllTest {

    @Test
    public void testPutAll_WithValidKeys() {
        // Given
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();
        Map<MultiKey<? extends String>, String> mapToCopy = new HashMap<>();
        mapToCopy.put(new MultiKey<>("key1", "key2"), "value1");
        mapToCopy.put(new MultiKey<>("key3", "key4"), "value2");

        // When
        multiKeyMap.putAll(mapToCopy);

        // Then
        // Assertions are implicit as the method does not return a value
    }

    @Test
    public void testPutAll_WithNullKey() {
        // Given
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();
        Map<MultiKey<? extends String>, String> mapToCopy = new HashMap<>();
        mapToCopy.put(null, "value1");

        // When & Then
        assertThrows(NullPointerException.class, () -> multiKeyMap.putAll(mapToCopy));
    }
}
