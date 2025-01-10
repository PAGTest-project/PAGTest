
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_containsValueTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.MINUTES);
    }

    @Test
    public void testContainsValue_ValuePresent() {
        expiringMap.put(1, "value1");
        expiringMap.put(2, "value2");

        assertTrue(expiringMap.containsValue("value1"));
        assertTrue(expiringMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        expiringMap.put(1, "value1");
        expiringMap.put(2, "value2");

        assertFalse(expiringMap.containsValue("value3"));
    }

    @Test
    public void testContainsValue_ExpiredValue() throws InterruptedException {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
        expiringMap.put(1, "value1");
        expiringMap.put(2, "value2");

        Thread.sleep(1500); // Wait for entries to expire

        assertFalse(expiringMap.containsValue("value1"));
        assertFalse(expiringMap.containsValue("value2"));
    }
}
