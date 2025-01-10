
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

    private PassiveExpiringMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
    }

    @Test
    public void testKeySetWithExpiredEntries() throws InterruptedException {
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        Thread.sleep(1500); // Ensure entries expire

        // When
        Set<String> keySet = map.keySet();

        // Then
        assertTrue(keySet.isEmpty());
    }

    @Test
    public void testKeySetWithoutExpiredEntries() {
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        Set<String> keySet = map.keySet();

        // Then
        assertEquals(2, keySet.size());
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
    }
}
