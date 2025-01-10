
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassiveExpiringMap_keySetTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
    }

    @Test
    public void testKeySetWithExpiredEntries() throws InterruptedException {
        // Given
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // When
        Thread.sleep(1500); // Wait for entries to expire
        Set<Integer> keySet = expiringMap.keySet();

        // Then
        assertTrue(keySet.isEmpty());
    }

    @Test
    public void testKeySetWithoutExpiredEntries() {
        // Given
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // When
        Set<Integer> keySet = expiringMap.keySet();

        // Then
        assertEquals(3, keySet.size());
        assertTrue(keySet.contains(1));
        assertTrue(keySet.contains(2));
        assertTrue(keySet.contains(3));
    }

    @Test
    public void testKeySetWithMixedExpiry() throws InterruptedException {
        // Given
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // When
        Thread.sleep(1500); // Wait for entries to expire
        expiringMap.put(4, "Four"); // New entry should not expire
        Set<Integer> keySet = expiringMap.keySet();

        // Then
        assertEquals(1, keySet.size());
        assertTrue(keySet.contains(4));
    }
}
