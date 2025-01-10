
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassiveExpiringMap_valuesTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
    }

    @Test
    public void testValuesWithNoExpiredEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        Collection<String> values = expiringMap.values();
        assertEquals(3, values.size());
        assertTrue(values.contains("One"));
        assertTrue(values.contains("Two"));
        assertTrue(values.contains("Three"));
    }

    @Test
    public void testValuesWithExpiredEntries() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Wait for entries to expire
        Thread.sleep(1500);

        Collection<String> values = expiringMap.values();
        assertEquals(0, values.size());
    }

    @Test
    public void testValuesWithMixedExpiredAndNonExpiredEntries() throws InterruptedException {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Wait for some entries to expire
        Thread.sleep(500);

        expiringMap.put(4, "Four");
        expiringMap.put(5, "Five");

        Collection<String> values = expiringMap.values();
        assertEquals(2, values.size());
        assertTrue(values.contains("Four"));
        assertTrue(values.contains("Five"));
    }

    @Test
    public void testValuesWithNeverExpiringEntries() {
        expiringMap = new PassiveExpiringMap<>(-1L);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        Collection<String> values = expiringMap.values();
        assertEquals(3, values.size());
        assertTrue(values.contains("One"));
        assertTrue(values.contains("Two"));
        assertTrue(values.contains("Three"));
    }

    @Test
    public void testValuesWithAlwaysExpiringEntries() {
        expiringMap = new PassiveExpiringMap<>(0L);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        Collection<String> values = expiringMap.values();
        assertEquals(0, values.size());
    }
}
