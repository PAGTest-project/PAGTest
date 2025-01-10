
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_putTest {

    private MultiKeyMap<String, String> multiKeyMap;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testPutWithFiveKeys() {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        String key4 = "key4";
        String key5 = "key5";
        String value = "value";

        // Test adding a new entry
        assertNull(multiKeyMap.put(key1, key2, key3, key4, key5, value));
        assertEquals(1, multiKeyMap.size());
        assertEquals(value, multiKeyMap.get(key1, key2, key3, key4, key5));
        assertTrue(multiKeyMap.containsKey(key1, key2, key3, key4, key5));
        assertTrue(multiKeyMap.containsKey(new MultiKey<>(key1, key2, key3, key4, key5)));

        // Test updating an existing entry
        String newValue = "newValue";
        assertEquals(value, multiKeyMap.put(key1, key2, key3, key4, key5, newValue));
        assertEquals(1, multiKeyMap.size());
        assertEquals(newValue, multiKeyMap.get(key1, key2, key3, key4, key5));
        assertTrue(multiKeyMap.containsKey(key1, key2, key3, key4, key5));
        assertTrue(multiKeyMap.containsKey(new MultiKey<>(key1, key2, key3, key4, key5)));

        // Test adding a null value
        assertEquals(newValue, multiKeyMap.put(key1, key2, key3, key4, key5, null));
        assertEquals(1, multiKeyMap.size());
        assertNull(multiKeyMap.get(key1, key2, key3, key4, key5));
        assertTrue(multiKeyMap.containsKey(key1, key2, key3, key4, key5));
        assertTrue(multiKeyMap.containsKey(new MultiKey<>(key1, key2, key3, key4, key5)));
    }

    @Test
    public void testPutWithFiveKeys_DifferentKeys() {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        String key4 = "key4";
        String key5 = "key5";
        String value1 = "value1";

        String key6 = "key6";
        String key7 = "key7";
        String key8 = "key8";
        String key9 = "key9";
        String key10 = "key10";
        String value2 = "value2";

        // Test adding two different entries
        assertNull(multiKeyMap.put(key1, key2, key3, key4, key5, value1));
        assertNull(multiKeyMap.put(key6, key7, key8, key9, key10, value2));
        assertEquals(2, multiKeyMap.size());
        assertEquals(value1, multiKeyMap.get(key1, key2, key3, key4, key5));
        assertEquals(value2, multiKeyMap.get(key6, key7, key8, key9, key10));
        assertTrue(multiKeyMap.containsKey(key1, key2, key3, key4, key5));
        assertTrue(multiKeyMap.containsKey(key6, key7, key8, key9, key10));
        assertTrue(multiKeyMap.containsKey(new MultiKey<>(key1, key2, key3, key4, key5)));
        assertTrue(multiKeyMap.containsKey(new MultiKey<>(key6, key7, key8, key9, key10)));
    }
}
