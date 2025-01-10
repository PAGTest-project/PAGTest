
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
    public void testKeySetWithMixedExpiry() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Wait for some entries to expire
        Thread.sleep(500);

        expiringMap.put(4, "Four");
        expiringMap.put(5, "Five");

        Set<Integer> keySet = expiringMap.keySet();
        assertEquals(5, keySet.size());

        // Wait for all entries to expire
        Thread.sleep(1000);

        keySet = expiringMap.keySet();
        assertTrue(keySet.isEmpty());
    }

    private Map<Integer, String> makeTestMap() {
        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "One");
        testMap.put(2, "Two");
        testMap.put(3, "Three");
        return testMap;
    }
}
