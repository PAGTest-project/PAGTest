
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassiveExpiringMap_entrySetTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(-1L), new HashMap<>());
    }

    @Test
    public void testEntrySet() {
        // Given
        Map<Integer, String> testMap = makeTestMap();
        expiringMap.putAll(testMap);

        // When
        Set<Map.Entry<Integer, String>> entrySet = expiringMap.entrySet();

        // Then
        assertEquals(testMap.size(), entrySet.size());
    }

    private Map<Integer, String> makeTestMap() {
        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "One");
        testMap.put(2, "Two");
        testMap.put(3, "Three");
        return testMap;
    }
}
