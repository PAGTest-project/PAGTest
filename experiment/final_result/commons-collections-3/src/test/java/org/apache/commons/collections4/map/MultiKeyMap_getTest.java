
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testGetWithNullKeys() {
        multiKeyMap.put(null, null, "null-null");
        assertEquals("null-null", multiKeyMap.get(null, null));
    }

    @Test
    public void testGetWithOneNullKey() {
        multiKeyMap.put(1, null, "1-null");
        assertEquals("1-null", multiKeyMap.get(1, null));
    }

    @Test
    public void testGetWithDifferentKeys() {
        multiKeyMap.put(1, 2, "1-2");
        multiKeyMap.put(3, 4, "3-4");
        assertEquals("1-2", multiKeyMap.get(1, 2));
        assertEquals("3-4", multiKeyMap.get(3, 4));
    }
}
