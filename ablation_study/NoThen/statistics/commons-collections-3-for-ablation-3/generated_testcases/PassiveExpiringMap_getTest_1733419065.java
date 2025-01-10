
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PassiveExpiringMap_getTest {

    @Test
    public void testGet_EntryExistsAndNotExpired() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());
        map.put("key1", "value1");

        // When
        String result = map.get("key1");

        // Then
        assertEquals("value1", result);
    }

    @Test
    public void testGet_EntryExistsButExpired() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new ConstantTimeToLiveExpirationPolicy<>(0L), new HashMap<>());
        map.put("key1", "value1");

        // When
        String result = map.get("key1");

        // Then
        assertNull(result);
    }

    @Test
    public void testGet_EntryDoesNotExist() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(new ConstantTimeToLiveExpirationPolicy<>(1000L), new HashMap<>());

        // When
        String result = map.get("key1");

        // Then
        assertNull(result);
    }
}
