
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassiveExpiringMap_isEmptyTest {

    @Test
    public void testIsEmpty_WithNonExpiredEntries() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(1000L, map);

        // When
        boolean isEmpty = expiringMap.isEmpty();

        // Then
        assertFalse(isEmpty);
    }

    @Test
    public void testIsEmpty_WithAllExpiredEntries() throws InterruptedException {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(1L, map);

        // Wait for the entry to expire
        Thread.sleep(10);

        // Force removal of expired entries
        expiringMap.removeAllExpired();

        // When
        boolean isEmpty = expiringMap.isEmpty();

        // Then
        assertTrue(isEmpty);
    }
}
