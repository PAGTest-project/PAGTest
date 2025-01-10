
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_sizeTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
    }

    @Test
    public void testSizeWithNoExpiredEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        assertEquals(2, expiringMap.size());
    }

    @Test
    public void testSizeWithExpiredEntries() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        Thread.sleep(1500); // Wait for entries to expire
        assertEquals(0, expiringMap.size());
    }

    @Test
    public void testSizeWithMixedExpiredAndNonExpiredEntries() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        Thread.sleep(500); // Wait for partial expiration
        expiringMap.put(3, "Three");
        assertEquals(1, expiringMap.size());
    }

    @Test
    public void testSizeWithNeverExpiringEntries() {
        expiringMap = new PassiveExpiringMap<>(-1L);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        assertEquals(2, expiringMap.size());
    }

    @Test
    public void testSizeWithAlwaysExpiringEntries() {
        expiringMap = new PassiveExpiringMap<>(0L);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        assertEquals(0, expiringMap.size());
    }

    @Test
    public void testSizeWithEmptyMap() {
        assertEquals(0, expiringMap.size());
    }

    @Test
    public void testSizeWithCustomExpirationPolicy() {
        ExpirationPolicy<Integer, String> customPolicy = (key, value) -> key == 1 ? -1L : 0L;
        expiringMap = new PassiveExpiringMap<>(customPolicy);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        assertEquals(1, expiringMap.size());
    }
}
