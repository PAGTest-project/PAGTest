
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_removeTest {

    private PassiveExpiringMap<String, String> map;
    private Map<String, String> internalMap;

    @BeforeEach
    public void setUp() {
        internalMap = new HashMap<>();
        map = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(-1L), internalMap);
    }

    @Test
    public void testRemove_KeyExists() {
        internalMap.put("key1", "value1");
        map.expirationMap.put("key1", -1L);

        String result = map.remove("key1");

        assertEquals("value1", result);
        assertNull(internalMap.get("key1"));
        assertNull(map.expirationMap.get("key1"));
    }

    @Test
    public void testRemove_KeyDoesNotExist() {
        String result = map.remove("key2");

        assertNull(result);
        assertNull(internalMap.get("key2"));
        assertNull(map.expirationMap.get("key2"));
    }
}
