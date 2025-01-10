
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_containsValueTest {

    private PassiveExpiringMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
    }

    @Test
    public void testContainsValue_ValuePresentAndNotExpired() {
        map.put("key1", "value1");
        assertTrue(map.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValuePresentButExpired() throws InterruptedException {
        map.put("key2", "value2");
        Thread.sleep(1500); // Wait for the entry to expire
        assertFalse(map.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        assertFalse(map.containsValue("value3"));
    }
}
