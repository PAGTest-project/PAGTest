
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_removeMultiKeyTest {
    private MultiKeyMap<String, String> multiKeyMap;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testRemoveMultiKey_ExistingKey() {
        multiKeyMap.put("key1", "key2", "value1");
        String removedValue = multiKeyMap.removeMultiKey("key1", "key2");
        assertEquals("value1", removedValue);
        assertFalse(multiKeyMap.containsKey("key1", "key2"));
    }

    @Test
    public void testRemoveMultiKey_NonExistingKey() {
        String removedValue = multiKeyMap.removeMultiKey("key1", "key2");
        assertNull(removedValue);
    }

    @Test
    public void testRemoveMultiKey_NullKey() {
        multiKeyMap.put(null, "key2", "value1");
        String removedValue = multiKeyMap.removeMultiKey(null, "key2");
        assertEquals("value1", removedValue);
        assertFalse(multiKeyMap.containsKey(null, "key2"));
    }

    @Test
    public void testRemoveMultiKey_MultipleEntries() {
        multiKeyMap.put("key1", "key2", "value1");
        multiKeyMap.put("key3", "key4", "value2");
        String removedValue1 = multiKeyMap.removeMultiKey("key1", "key2");
        String removedValue2 = multiKeyMap.removeMultiKey("key3", "key4");
        assertEquals("value1", removedValue1);
        assertEquals("value2", removedValue2);
        assertFalse(multiKeyMap.containsKey("key1", "key2"));
        assertFalse(multiKeyMap.containsKey("key3", "key4"));
    }

    @Test
    public void testRemoveMultiKey_SameHashCodeDifferentKeys() {
        multiKeyMap.put("AaAa", "BBBB", "value1");
        multiKeyMap.put("BBAa", "AaBB", "value2");
        String removedValue1 = multiKeyMap.removeMultiKey("AaAa", "BBBB");
        String removedValue2 = multiKeyMap.removeMultiKey("BBAa", "AaBB");
        assertEquals("value1", removedValue1);
        assertEquals("value2", removedValue2);
        assertFalse(multiKeyMap.containsKey("AaAa", "BBBB"));
        assertFalse(multiKeyMap.containsKey("BBAa", "AaBB"));
    }
}
