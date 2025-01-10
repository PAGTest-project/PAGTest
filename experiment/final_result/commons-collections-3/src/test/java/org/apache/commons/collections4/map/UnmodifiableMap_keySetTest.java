
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_keySetTest {

    @Test
    public void testKeySet() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        Map<String, String> unmodifiableMap = UnmodifiableMap.unmodifiableMap(originalMap);

        // When
        Set<String> keySet = unmodifiableMap.keySet();

        // Then
        assertTrue(keySet instanceof UnmodifiableSet);
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
    }
}
