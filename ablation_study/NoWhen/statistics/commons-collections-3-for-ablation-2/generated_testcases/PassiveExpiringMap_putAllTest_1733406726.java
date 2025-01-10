
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_putAllTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(-1L); // Entries never expire
    }

    @Test
    public void testPutAll() {
        Map<Integer, String> mapToCopy = new HashMap<>();
        mapToCopy.put(1, "One");
        mapToCopy.put(2, "Two");
        mapToCopy.put(3, "Three");

        expiringMap.putAll(mapToCopy);

        assertEquals(3, expiringMap.size());
        assertEquals("One", expiringMap.get(1));
        assertEquals("Two", expiringMap.get(2));
        assertEquals("Three", expiringMap.get(3));
    }

    @Test
    public void testPutAllWithEmptyMap() {
        Map<Integer, String> mapToCopy = new HashMap<>();

        expiringMap.putAll(mapToCopy);

        assertEquals(0, expiringMap.size());
    }

    @Test
    public void testPutAllWithExistingEntries() {
        expiringMap.put(1, "OriginalOne");
        expiringMap.put(2, "OriginalTwo");

        Map<Integer, String> mapToCopy = new HashMap<>();
        mapToCopy.put(1, "NewOne");
        mapToCopy.put(3, "Three");

        expiringMap.putAll(mapToCopy);

        assertEquals(3, expiringMap.size());
        assertEquals("NewOne", expiringMap.get(1));
        assertEquals("OriginalTwo", expiringMap.get(2));
        assertEquals("Three", expiringMap.get(3));
    }

    @Test
    public void testPutAllWithExpiredEntries() throws InterruptedException {
        expiringMap = new PassiveExpiringMap<>(1L); // Entries expire in 1 millisecond
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");

        Thread.sleep(2); // Ensure entries expire

        Map<Integer, String> mapToCopy = new HashMap<>();
        mapToCopy.put(3, "Three");
        mapToCopy.put(4, "Four");

        expiringMap.putAll(mapToCopy);

        assertEquals(2, expiringMap.size());
        assertNull(expiringMap.get(1));
        assertNull(expiringMap.get(2));
        assertEquals("Three", expiringMap.get(3));
        assertEquals("Four", expiringMap.get(4));
    }
}
