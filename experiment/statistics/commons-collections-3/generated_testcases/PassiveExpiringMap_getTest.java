
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PassiveExpiringMap_getTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
    }

    @Test
    public void testGetNonExpiredEntry() {
        expiringMap.put(1, "one");
        assertEquals("one", expiringMap.get(1));
    }

    @Test
    public void testGetExpiredEntry() throws InterruptedException {
        expiringMap.put(1, "one");
        Thread.sleep(1500); // Wait for the entry to expire
        assertNull(expiringMap.get(1));
    }

    @Test
    public void testGetNonExistentEntry() {
        assertNull(expiringMap.get(2));
    }
}
