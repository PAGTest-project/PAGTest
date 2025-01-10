
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_valuesTest {

    @Test
    public void testValuesWithExpiredEntries() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(1, TimeUnit.MILLISECONDS);
        map.put("key1", "value1");
        map.put("key2", "value2");

        // Wait for entries to expire
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Collection<String> values = map.values();
        assertEquals(0, values.size());
    }

    @Test
    public void testValuesWithoutExpiredEntries() {
        PassiveExpiringMap<String, String> map = new PassiveExpiringMap<>(1000, TimeUnit.MILLISECONDS);
        map.put("key1", "value1");
        map.put("key2", "value2");

        Collection<String> values = map.values();
        assertEquals(2, values.size());
    }
}
