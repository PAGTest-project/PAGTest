
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testIsEmpty_EmptyMap() {
        assertTrue(expiringMap.isEmpty());
    }

    @Test
    public void testIsEmpty_NonEmptyMap() {
        expiringMap.put(1, "value1");
        assertFalse(expiringMap.isEmpty());
    }

    @Test
    public void testIsEmpty_ExpiredEntries() throws InterruptedException {
        expiringMap.put(1, "value1");
        Thread.sleep(1100); // Wait for the entry to expire
        assertTrue(expiringMap.isEmpty());
    }
}
