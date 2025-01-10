
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultiKeyMap_removeMultiKeyTest {

    private MultiKeyMap<String, String> map;

    @BeforeEach
    void setUp() {
        map = new MultiKeyMap<>();
    }

    @Test
    void testRemoveMultiKey_KeyExists() {
        map.put("key1", "key2", "value1");
        String removedValue = map.removeMultiKey("key1", "key2");
        assertEquals("value1", removedValue);
        assertFalse(map.containsKey("key1", "key2"));
    }

    @Test
    void testRemoveMultiKey_KeyDoesNotExist() {
        String removedValue = map.removeMultiKey("key1", "key2");
        assertNull(removedValue);
    }
}
