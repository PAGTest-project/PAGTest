
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_valuesTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
        expiringMap.put(1, "One");
        expiringMap.put(2, "Two");
        expiringMap.put(3, "Three");
    }

    @Test
    public void testValuesWithExpiredEntries() throws InterruptedException {
        // Wait for entries to expire
        Thread.sleep(1500);

        Collection<String> values = expiringMap.values();
        assertTrue(values.isEmpty());
    }

    @Test
    public void testValuesWithoutExpiredEntries() {
        Collection<String> values = expiringMap.values();
        assertEquals(3, values.size());
        assertTrue(values.contains("One"));
        assertTrue(values.contains("Two"));
        assertTrue(values.contains("Three"));
    }
}
