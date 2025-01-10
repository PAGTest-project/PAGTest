
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
    private ExpirationPolicy<Integer, String> expiringPolicy;

    @BeforeEach
    public void setUp() {
        expiringPolicy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1, TimeUnit.MINUTES);
        expiringMap = new PassiveExpiringMap<>(expiringPolicy, new HashMap<>());
    }

    @Test
    public void testPut_NewEntry() {
        Integer key = 1;
        String value = "one";

        String result = expiringMap.put(key, value);

        assertNull(result);
        assertEquals(value, expiringMap.get(key));
    }

    @Test
    public void testPut_ReplaceEntry() {
        Integer key = 1;
        String oldValue = "one";
        String newValue = "newOne";

        expiringMap.put(key, oldValue);
        String result = expiringMap.put(key, newValue);

        assertEquals(oldValue, result);
        assertEquals(newValue, expiringMap.get(key));
    }

    @Test
    public void testPut_ExpiredEntry() {
        Integer key = 1;
        String value = "one";

        expiringMap.put(key, value);
        expiringMap.removeIfExpired(key, System.currentTimeMillis() + 2 * 60 * 1000); // Simulate expiration

        String result = expiringMap.put(key, value);

        assertNull(result);
        assertEquals(value, expiringMap.get(key));
    }
}
