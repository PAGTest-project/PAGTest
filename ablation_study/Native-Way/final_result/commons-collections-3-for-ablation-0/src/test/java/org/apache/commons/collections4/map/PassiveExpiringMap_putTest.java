
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_putTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        PassiveExpiringMap.ExpirationPolicy<Integer, String> policy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1, TimeUnit.MINUTES);
        expiringMap = new PassiveExpiringMap<>(policy, new HashMap<>());
    }

    @Test
    public void testPutNewEntry() {
        String oldValue = expiringMap.put(1, "value1");
        assertNull(oldValue);
        assertEquals("value1", expiringMap.get(1));
    }

    @Test
    public void testPutReplaceEntry() {
        expiringMap.put(1, "value1");
        String oldValue = expiringMap.put(1, "newValue1");
        assertEquals("value1", oldValue);
        assertEquals("newValue1", expiringMap.get(1));
    }

    @Test
    public void testPutExpiredEntry() {
        expiringMap.put(1, "value1");
        expiringMap.remove(1); // Use public remove method instead of private removeIfExpired
        String oldValue = expiringMap.put(1, "newValue1");
        assertNull(oldValue);
        assertEquals("newValue1", expiringMap.get(1));
    }

    @Test
    public void testPutWithNegativeTimeToLive() {
        PassiveExpiringMap.ExpirationPolicy<Integer, String> policy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(-1);
        expiringMap = new PassiveExpiringMap<>(policy, new HashMap<>());
        expiringMap.put(1, "value1");
        String oldValue = expiringMap.put(1, "newValue1");
        assertEquals("value1", oldValue);
        assertEquals("newValue1", expiringMap.get(1));
    }

    @Test
    public void testPutWithZeroTimeToLive() {
        PassiveExpiringMap.ExpirationPolicy<Integer, String> policy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(0);
        expiringMap = new PassiveExpiringMap<>(policy, new HashMap<>());
        expiringMap.put(1, "value1");
        String oldValue = expiringMap.put(1, "newValue1");
        assertNull(oldValue);
        assertNull(expiringMap.get(1));
    }
}
