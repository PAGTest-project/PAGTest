
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_entrySetTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
    }

    @Test
    public void testEntrySetWithExpiredEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Wait for entries to expire
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Set<Map.Entry<Integer, String>> entrySet = expiringMap.entrySet();
        assertTrue(entrySet.isEmpty());
    }

    @Test
    public void testEntrySetWithNonExpiredEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        Set<Map.Entry<Integer, String>> entrySet = expiringMap.entrySet();
        assertEquals(3, entrySet.size());
    }

    @Test
    public void testEntrySetAfterPartialExpiration() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Wait for entries to expire partially
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        expiringMap.put(4, "Four");

        Set<Map.Entry<Integer, String>> entrySet = expiringMap.entrySet();
        assertEquals(4, entrySet.size());
    }

    @Test
    public void testEntrySetAfterClear() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        expiringMap.clear();

        Set<Map.Entry<Integer, String>> entrySet = expiringMap.entrySet();
        assertTrue(entrySet.isEmpty());
    }
}
