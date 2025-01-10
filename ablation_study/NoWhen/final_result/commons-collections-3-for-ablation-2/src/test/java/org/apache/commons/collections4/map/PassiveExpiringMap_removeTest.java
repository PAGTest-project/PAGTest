
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class PassiveExpiringMap_removeTest {

    @Test
    public void testRemove() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        PassiveExpiringMap<String, String> expiringMap = new PassiveExpiringMap<>(map);

        // When
        String removedValue = expiringMap.remove("key1");

        // Then
        assertNull(expiringMap.get("key1"));
        assertEquals("value1", removedValue);
    }
}
