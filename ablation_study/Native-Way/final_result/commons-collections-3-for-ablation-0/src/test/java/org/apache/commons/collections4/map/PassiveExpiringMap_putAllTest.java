
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_putAllTest {

    @Test
    public void testPutAll() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(-1L));
        Map<String, String> mapToCopy = new HashMap<>();
        mapToCopy.put("key1", "value1");
        mapToCopy.put("key2", "value2");

        map.putAll(mapToCopy);

        assertEquals(2, map.size());
        assertTrue(map.containsKey("key1"));
        assertTrue(map.containsKey("key2"));
    }
}
