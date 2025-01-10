
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PassiveExpiringMap_getTest {

    @Test
    public void testGet_EntryNotExpired() {
        // Given
        Map<String, String> map = new HashMap<>();
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(1000L, map);
        expiringMap.put("key1", "value1");

        // When
        String result = expiringMap.get("key1");

        // Then
        assertEquals("value1", result);
    }

    @Test
    public void testGet_EntryExpired() throws InterruptedException {
        // Given
        Map<String, String> map = new HashMap<>();
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(100L, map);
        expiringMap.put("key1", "value1");

        // When
        Thread.sleep(200L); // Ensure entry is expired
        String result = expiringMap.get("key1");

        // Then
        assertNull(result);
    }
}
