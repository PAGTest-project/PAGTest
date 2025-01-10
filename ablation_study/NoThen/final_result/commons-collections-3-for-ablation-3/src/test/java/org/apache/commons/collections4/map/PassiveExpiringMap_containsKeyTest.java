
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PassiveExpiringMap_containsKeyTest {

    private PassiveExpiringMap<String, String> map;

    @BeforeEach
    void setUp() {
        map = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
    }

    @Test
    void testContainsKey_KeyExistsAndNotExpired() {
        map.put("key1", "value1");
        assertTrue(map.containsKey("key1"));
    }

    @Test
    void testContainsKey_KeyExistsButExpired() throws InterruptedException {
        map.put("key2", "value2");
        Thread.sleep(1001); // Wait for the entry to expire
        assertFalse(map.containsKey("key2"));
    }

    @Test
    void testContainsKey_KeyDoesNotExist() {
        assertFalse(map.containsKey("key3"));
    }
}
