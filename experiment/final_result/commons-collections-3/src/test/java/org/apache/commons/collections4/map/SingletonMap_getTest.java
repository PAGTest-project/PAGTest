
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonMap_getTest {

    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testGetExistingKey() {
        assertEquals("value", singletonMap.get("key"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(singletonMap.get("nonExistingKey"));
    }
}
