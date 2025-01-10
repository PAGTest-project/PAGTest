
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_getTest {

    private MultiKeyMap<String, String> multiKeyMap;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testGetExistingKey() {
        multiKeyMap.put("key1", "key2", "value1");
        assertEquals("value1", multiKeyMap.get("key1", "key2"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(multiKeyMap.get("nonExistingKey1", "nonExistingKey2"));
    }

    @Test
    public void testGetWithNullKey() {
        multiKeyMap.put(null, "key2", "value2");
        assertEquals("value2", multiKeyMap.get(null, "key2"));
    }

    @Test
    public void testGetWithBothNullKeys() {
        multiKeyMap.put(null, null, "value3");
        assertEquals("value3", multiKeyMap.get(null, null));
    }

    @Test
    public void testGetAfterRemove() {
        multiKeyMap.put("key1", "key2", "value1");
        multiKeyMap.removeMultiKey("key1", "key2");
        assertNull(multiKeyMap.get("key1", "key2"));
    }

    @Test
    public void testGetWithMultipleEntries() {
        multiKeyMap.put("key1", "key2", "value1");
        multiKeyMap.put("key3", "key4", "value2");
        assertEquals("value1", multiKeyMap.get("key1", "key2"));
        assertEquals("value2", multiKeyMap.get("key3", "key4"));
    }

    @Test
    public void testGetWithSameHashCodeDifferentKeys() {
        String key1 = "Aa";
        String key2 = "BB";
        assertTrue(key1.hashCode() == key2.hashCode());

        multiKeyMap.put(key1, key1, "value1");
        multiKeyMap.put(key2, key2, "value2");

        assertEquals("value1", multiKeyMap.get(key1, key1));
        assertEquals("value2", multiKeyMap.get(key2, key2));
    }
}
