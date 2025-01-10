
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_keySetTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
    }

    @Test
    public void testKeySetWithExpiredEntries() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Wait for entries to expire
        Thread.sleep(1500);

        Set<Integer> keySet = expiringMap.keySet();
        assertTrue(keySet.isEmpty());
    }

    @Test
    public void testKeySetWithoutExpiredEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        Set<Integer> keySet = expiringMap.keySet();
        assertEquals(3, keySet.size());
    }

    @Test
    public void testKeySetAfterPartialExpiration() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Wait for entries to expire
        Thread.sleep(1500);

        expiringMap.put(4, "Four");

        Set<Integer> keySet = expiringMap.keySet();
        assertEquals(1, keySet.size());
        assertTrue(keySet.contains(4));
    }

    @Test
    public void testKeySetWithNeverExpiringEntries() {
        expiringMap = new PassiveExpiringMap<>(-1L);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        Set<Integer> keySet = expiringMap.keySet();
        assertEquals(3, keySet.size());
    }

    @Test
    public void testKeySetWithZeroTimeToLive() {
        expiringMap = new PassiveExpiringMap<>(0L);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        Set<Integer> keySet = expiringMap.keySet();
        assertTrue(keySet.isEmpty());
    }
}
