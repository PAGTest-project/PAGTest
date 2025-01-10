
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_cloneTest {

    private MultiKeyMap<String, String> multiKeyMap;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testClone() {
        multiKeyMap.put("key1", "key2", "value1");
        MultiKeyMap<String, String> clonedMap = multiKeyMap.clone();

        assertEquals(multiKeyMap.size(), clonedMap.size());
        assertEquals("value1", clonedMap.get("key1", "key2"));
    }

    @Test
    public void testCloneEmptyMap() {
        MultiKeyMap<String, String> clonedMap = multiKeyMap.clone();

        assertEquals(0, clonedMap.size());
    }

    @Test
    public void testCloneWithMultipleKeys() {
        multiKeyMap.put("key1", "key2", "key3", "key4", "key5", "value2");
        MultiKeyMap<String, String> clonedMap = multiKeyMap.clone();

        assertEquals(multiKeyMap.size(), clonedMap.size());
        assertEquals("value2", clonedMap.get("key1", "key2", "key3", "key4", "key5"));
    }

    @Test
    public void testCloneWithNullValue() {
        multiKeyMap.put("key1", "key2", null);
        MultiKeyMap<String, String> clonedMap = multiKeyMap.clone();

        assertEquals(multiKeyMap.size(), clonedMap.size());
        assertNull(clonedMap.get("key1", "key2"));
    }
}
