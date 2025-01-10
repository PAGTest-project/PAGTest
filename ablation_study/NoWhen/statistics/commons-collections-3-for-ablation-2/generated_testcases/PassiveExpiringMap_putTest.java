
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PassiveExpiringMap_putTest {

    @Test
    void testPut() {
        // Given
        PassiveExpiringMap.ExpirationPolicy<String, String> policy = (key, value) -> System.currentTimeMillis() + 1000;
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(policy, new HashMap<>());
        String key = "testKey";
        String value = "testValue";

        // When
        map.put(key, value);

        // Then
        assertTrue(map.containsKey(key));
        assertEquals(value, map.get(key));
    }
}
