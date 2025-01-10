
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PassiveExpiringMap_containsValueTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(1, TimeUnit.SECONDS);
    }

    @Test
    public void testContainsValue_ValuePresent() {
        expiringMap.put(1, "one");
        expiringMap.put(2, "two");
        assertTrue(expiringMap.containsValue("one"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        expiringMap.put(1, "one");
        assertFalse(expiringMap.containsValue("three"));
    }

    @Test
    public void testContainsValue_ValueExpired() throws InterruptedException {
        expiringMap.put(1, "one");
        Thread.sleep(1500); // Wait for the entry to expire
        assertFalse(expiringMap.containsValue("one"));
    }

    @Test
    public void testContainsValue_EmptyMap() {
        assertFalse(expiringMap.containsValue("one"));
    }

    @Test
    public void testContainsValue_NullValue() {
        expiringMap.put(1, null);
        assertTrue(expiringMap.containsValue(null));
    }

    @Test
    public void testContainsValue_MultipleValues() {
        expiringMap.put(1, "one");
        expiringMap.put(2, "two");
        expiringMap.put(3, "three");
        assertTrue(expiringMap.containsValue("two"));
    }

    @Test
    public void testContainsValue_MultipleValuesWithExpired() throws InterruptedException {
        expiringMap.put(1, "one");
        expiringMap.put(2, "two");
        expiringMap.put(3, "three");
        Thread.sleep(1500); // Wait for the entries to expire
        assertFalse(expiringMap.containsValue("two"));
    }

    @Test
    public void testContainsValue_NeverExpire() {
        expiringMap = new PassiveExpiringMap<>(-1L);
        expiringMap.put(1, "one");
        assertTrue(expiringMap.containsValue("one"));
    }

    @Test
    public void testContainsValue_AlwaysExpire() {
        expiringMap = new PassiveExpiringMap<>(0L);
        expiringMap.put(1, "one");
        assertFalse(expiringMap.containsValue("one"));
    }
}
