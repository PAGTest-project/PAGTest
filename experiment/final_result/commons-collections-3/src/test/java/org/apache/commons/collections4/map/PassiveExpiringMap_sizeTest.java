
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_sizeTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
    }

    @Test
    public void testSizeWithExpiredEntries() {
        expiringMap.put(1, "one");
        expiringMap.put(2, "two");

        // Wait for entries to expire
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertEquals(0, expiringMap.size());
    }

    @Test
    public void testSizeWithNonExpiredEntries() {
        expiringMap.put(1, "one");
        expiringMap.put(2, "two");

        assertEquals(2, expiringMap.size());
    }
}
