
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_valuesTest {

    private PassiveExpiringMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
    }

    @Test
    public void testValuesWithExpiredEntries() {
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key1", "value1", System.currentTimeMillis() - 2000L); // Expired
        map.put("key2", "value2", System.currentTimeMillis() + 2000L); // Not expired

        // When
        int sizeBefore = map.values().size();
        map.values(); // This will remove expired entries
        int sizeAfter = map.values().size();

        // Then
        assertEquals(2, sizeBefore);
        assertEquals(1, sizeAfter);
    }
}
