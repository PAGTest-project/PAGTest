
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_putAllTest {

    private PassiveExpiringMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(-1L), new HashMap<>());
    }

    @Test
    public void testPutAll() {
        Map<String, String> mapToCopy = new HashMap<>();
        mapToCopy.put("key1", "value1");
        mapToCopy.put("key2", "value2");

        map.putAll(mapToCopy);

        assertEquals(2, map.size());
        assertTrue(map.containsKey("key1"));
        assertTrue(map.containsKey("key2"));
    }
}
