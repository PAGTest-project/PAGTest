
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.keyvalue.MultiKey;

public class MultiKeyMap_getTest {

    private MultiKeyMap<Integer, String> multiKeyMap;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testGetExistingKey() {
        multiKeyMap.put(1, 2, "1-2");
        assertEquals("1-2", multiKeyMap.get(1, 2));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(multiKeyMap.get(1, 2));
    }

    @Test
    public void testGetAfterRemove() {
        multiKeyMap.put(1, 2, "1-2");
        multiKeyMap.removeMultiKey(1, 2);
        assertNull(multiKeyMap.get(1, 2));
    }

    @Test
    public void testGetWithNullKeys() {
        multiKeyMap.put(null, null, "null-null");
        assertEquals("null-null", multiKeyMap.get(null, null));
    }

    @Test
    public void testGetWithMixedKeys() {
        multiKeyMap.put(1, null, "1-null");
        multiKeyMap.put(null, 2, "null-2");
        assertEquals("1-null", multiKeyMap.get(1, null));
        assertEquals("null-2", multiKeyMap.get(null, 2));
    }

    @Test
    public void testGetWithMultipleEntries() {
        multiKeyMap.put(1, 2, "1-2");
        multiKeyMap.put(1, 3, "1-3");
        multiKeyMap.put(2, 2, "2-2");
        assertEquals("1-2", multiKeyMap.get(1, 2));
        assertEquals("1-3", multiKeyMap.get(1, 3));
        assertEquals("2-2", multiKeyMap.get(2, 2));
    }

    @Test
    public void testGetWithOverwrittenEntry() {
        multiKeyMap.put(1, 2, "1-2");
        multiKeyMap.put(1, 2, "new-1-2");
        assertEquals("new-1-2", multiKeyMap.get(1, 2));
    }
}
