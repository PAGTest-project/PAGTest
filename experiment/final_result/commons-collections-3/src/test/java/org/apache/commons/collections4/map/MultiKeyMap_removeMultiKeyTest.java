
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_removeMultiKeyTest {

    private MultiKeyMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiKeyMap<>();
    }

    @Test
    public void testRemoveMultiKey_KeyExists() {
        map.put("key1", "key2", "value1");
        String removedValue = map.removeMultiKey("key1", "key2");
        assertEquals("value1", removedValue);
        assertNull(map.get("key1", "key2"));
    }

    @Test
    public void testRemoveMultiKey_KeyDoesNotExist() {
        String removedValue = map.removeMultiKey("key1", "key2");
        assertNull(removedValue);
    }
}
