
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class SingletonMap_getTest {

    @Test
    public void testGet_KeyExists() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        assertEquals("value", map.get("key"));
    }

    @Test
    public void testGet_KeyDoesNotExist() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        assertNull(map.get("nonexistentKey"));
    }
}
