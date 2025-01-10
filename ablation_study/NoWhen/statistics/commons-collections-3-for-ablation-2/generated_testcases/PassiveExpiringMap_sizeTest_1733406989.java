
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testSizeWithNullKey() {
        expiringMap.put(null, "Null");
        assertEquals(1, expiringMap.size());
    }

    @Test
    public void testSizeWithNullValue() {
        expiringMap.put(1, null);
        assertEquals(1, expiringMap.size());
    }

    @Test
    public void testSizeWithNullKeyAndValue() {
        expiringMap.put(null, null);
        assertEquals(1, expiringMap.size());
    }

    @Test
    public void testSizeWithMultiplePutsAndRemoves() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.remove(1);
        assertEquals(1, expiringMap.size());
        expiringMap.put(3, "Three");
        assertEquals(2, expiringMap.size());
    }
}
