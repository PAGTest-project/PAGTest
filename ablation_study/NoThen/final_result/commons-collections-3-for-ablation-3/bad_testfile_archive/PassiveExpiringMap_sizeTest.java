
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_sizeTest {

    @Test
    public void testSizeWithExpiredEntries() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(1000L, new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");

        // Simulate time passing to expire "key1"
        map.put("key1", "value1", System.currentTimeMillis() - 2000L);

        assertEquals(1, map.size());
    }

    @Test
    public void testSizeWithNoExpiredEntries() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(1000L, new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals(2, map.size());
    }
}
