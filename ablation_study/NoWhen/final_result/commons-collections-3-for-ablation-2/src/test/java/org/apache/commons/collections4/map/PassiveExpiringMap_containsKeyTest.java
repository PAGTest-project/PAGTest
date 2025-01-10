
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class PassiveExpiringMap_containsKeyTest {

    @Test
    public void testContainsKey_KeyExistsAndNotExpired() {
        Map<String, String> map = new HashMap<>();
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(1000L, map);
        expiringMap.put("key1", "value1");

        assertTrue(expiringMap.containsKey("key1"));
    }

    @Test
    public void testContainsKey_KeyExistsButExpired() {
        Map<String, String> map = new HashMap<>();
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(0L, map);
        expiringMap.put("key1", "value1");

        assertFalse(expiringMap.containsKey("key1"));
    }

    @Test
    public void testContainsKey_KeyDoesNotExist() {
        Map<String, String> map = new HashMap<>();
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(1000L, map);

        assertFalse(expiringMap.containsKey("key1"));
    }
}
