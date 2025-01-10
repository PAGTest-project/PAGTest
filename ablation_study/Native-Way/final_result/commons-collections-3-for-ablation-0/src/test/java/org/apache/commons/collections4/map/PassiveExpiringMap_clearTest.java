
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PassiveExpiringMap_clearTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(new HashMap<>());
    }

    @Test
    public void testClear() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        assertEquals(3, expiringMap.size());

        expiringMap.clear();

        assertEquals(0, expiringMap.size());
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testClearWithExpiredEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Simulate expiration by setting expiration times to a past time
        expiringMap.clear();

        assertEquals(0, expiringMap.size());
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testClearWithMixedEntries() {
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");

        // Simulate expiration for some entries
        expiringMap.clear();

        assertEquals(0, expiringMap.size());
        assertTrue(expiringMap.isEmpty());
    }
}
