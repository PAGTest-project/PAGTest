
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PassiveExpiringMap_putTest {

    @Test
    public void testPut_EntryNeverExpires() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(-1L);
        String key = "key";
        String value = "value";

        // When
        String result = map.put(key, value);

        // Then
        assertNull(result);
        assertEquals(value, map.get(key));
    }

    @Test
    public void testPut_EntryAlwaysExpires() {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(0L);
        String key = "key";
        String value = "value";

        // When
        String result = map.put(key, value);

        // Then
        assertNull(result);
        assertNull(map.get(key));
    }

    @Test
    public void testPut_EntryExpiresAfterTime() throws InterruptedException {
        // Given
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
        String key = "key";
        String value = "value";

        // When
        String result = map.put(key, value);

        // Then
        assertNull(result);
        assertEquals(value, map.get(key));

        // Wait for the entry to expire
        Thread.sleep(1100);
        assertNull(map.get(key));
    }
}
