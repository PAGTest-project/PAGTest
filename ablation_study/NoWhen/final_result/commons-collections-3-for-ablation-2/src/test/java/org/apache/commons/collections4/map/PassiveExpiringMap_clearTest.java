
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_clearTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(-1L); // Entries never expire
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");
    }

    @Test
    public void testClear() {
        expiringMap.clear();
        assertEquals(0, expiringMap.size());
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testClearWithExpiredEntries() {
        expiringMap = new PassiveExpiringMap<>(1L); // Entries expire after 1 millisecond
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        try {
            Thread.sleep(2); // Wait for entries to expire
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        expiringMap.clear();
        assertEquals(0, expiringMap.size());
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testClearWithNonExpiredEntries() {
        expiringMap = new PassiveExpiringMap<>(1000L); // Entries expire after 1 second
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        expiringMap.clear();
        assertEquals(0, expiringMap.size());
        assertTrue(expiringMap.isEmpty());
    }
}
