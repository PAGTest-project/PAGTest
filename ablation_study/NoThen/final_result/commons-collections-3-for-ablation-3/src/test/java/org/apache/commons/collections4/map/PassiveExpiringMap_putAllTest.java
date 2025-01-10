
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_putAllTest {

    @Test
    public void testPutAll() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(-1L));
        Map<String, String> mapToCopy = new HashMap<>();
        mapToCopy.put("key1", "value1");
        mapToCopy.put("key2", "value2");

        // When
        map.putAll(mapToCopy);

        // Then
        assertEquals(2, map.size());
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
    }
}
