
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.map.PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassiveExpiringMap_containsValueTest {

    @Test
    public void testContainsValue_ValuePresent() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new ConstantTimeToLiveExpirationPolicy<>(-1L), new HashMap<>());
        map.put("key1", "value1");
        assertTrue(map.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new ConstantTimeToLiveExpirationPolicy<>(-1L), new HashMap<>());
        map.put("key1", "value1");
        assertFalse(map.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ExpiredValue() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new ConstantTimeToLiveExpirationPolicy<>(0L), new HashMap<>());
        map.put("key1", "value1");
        assertFalse(map.containsValue("value1"));
    }
}
