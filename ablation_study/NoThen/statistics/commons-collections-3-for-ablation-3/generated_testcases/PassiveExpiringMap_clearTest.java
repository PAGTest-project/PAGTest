
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_clearTest {

    @Test
    public void testClear() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(map);

        // When
        expiringMap.clear();

        // Then
        assertEquals(0, expiringMap.size());
    }
}
