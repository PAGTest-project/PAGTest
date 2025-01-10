
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_isEmptyTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
    }

    @Test
    public void testIsEmpty_WithNoEntries() {
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testIsEmpty_WithNonExpiredEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        assertFalse(expiringMap.isEmpty());
    }

    @Test
    public void testIsEmpty_WithExpiredEntries() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        Thread.sleep(1100); // Wait for entries to expire
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testIsEmpty_WithMixedExpiry() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        Thread.sleep(500); // Wait for partial expiry
        assertFalse(expiringMap.isEmpty());
        Thread.sleep(600); // Wait for remaining entries to expire
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterClear() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.clear();
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterRemove() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.remove(1);
        assertFalse(expiringMap.isEmpty());
        expiringMap.remove(2);
        assertTrue(expiringMap.isEmpty());
    }
}
