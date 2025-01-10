
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassiveExpiringMap_removeTest {

    private PassiveExpiringMap<Integer, String> expiringMap;

    @BeforeEach
    public void setUp() {
        expiringMap = new PassiveExpiringMap<>(new HashMap<>());
    }

    @Test
    public void testRemoveExistingKey() {
        expiringMap.put(1, "value1");
        assertEquals("value1", expiringMap.remove(1));
        assertNull(expiringMap.get(1));
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertNull(expiringMap.remove(2));
    }
}
