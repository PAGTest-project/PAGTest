
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void testPutNewEntry() {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        String key4 = "key4";
        String key5 = "key5";
        String value = "value";

        assertNull(multiKeyMap.put(key1, key2, key3, key4, key5, value));
        assertEquals(value, multiKeyMap.get(key1, key2, key3, key4, key5));
    }

    @Test
    public void testPutExistingEntry() {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        String key4 = "key4";
        String key5 = "key5";
        String value1 = "value1";
        String value2 = "value2";

        assertNull(multiKeyMap.put(key1, key2, key3, key4, key5, value1));
        assertEquals(value1, multiKeyMap.put(key1, key2, key3, key4, key5, value2));
        assertEquals(value2, multiKeyMap.get(key1, key2, key3, key4, key5));
    }
}
