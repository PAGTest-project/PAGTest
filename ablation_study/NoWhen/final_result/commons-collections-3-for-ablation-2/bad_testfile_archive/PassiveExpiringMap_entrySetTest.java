
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_entrySetTest {

    @Test
    public void testEntrySetWithExpiredEntries() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(1000L, new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");

        // Simulate time passing to expire "key1"
        map.put("key1", "value1", System.currentTimeMillis() - 2000L);

        // When
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        // Then
        assertEquals(1, entrySet.size());
        assertEquals("value2", entrySet.iterator().next().getValue());
    }

    @Test
    public void testEntrySetWithoutExpiredEntries() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(1000L, new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        // Then
        assertEquals(2, entrySet.size());
    }
}
