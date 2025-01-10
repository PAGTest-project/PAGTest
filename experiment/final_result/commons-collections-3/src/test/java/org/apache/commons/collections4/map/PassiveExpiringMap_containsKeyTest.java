
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_containsKeyTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
    }

    @Test
    public void testContainsKey_KeyExistsAndNotExpired() {
        expiringMap.put(1, "one");
        assertTrue(expiringMap.containsKey(1));
    }

    @Test
    public void testContainsKey_KeyExistsButExpired() throws InterruptedException {
        expiringMap.put(1, "one");
        Thread.sleep(1500); // Wait for the entry to expire
        assertFalse(expiringMap.containsKey(1));
    }

    @Test
    public void testContainsKey_KeyDoesNotExist() {
        assertFalse(expiringMap.containsKey(2));
    }
}
