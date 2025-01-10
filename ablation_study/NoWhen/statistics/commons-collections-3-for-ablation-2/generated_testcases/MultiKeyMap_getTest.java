
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_getTest {
    private MultiKeyMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiKeyMap<>();
    }

    @Test
    public void testGetExistingKey() {
        map.put("a", "b", "value1");
        assertEquals("value1", map.get("a", "b"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(map.get("x", "y"));
    }

    @Test
    public void testGetWithNullKey() {
        map.put("a", null, "value2");
        assertEquals("value2", map.get("a", null));
    }

    @Test
    public void testGetWithBothNullKeys() {
        map.put(null, null, "value3");
        assertEquals("value3", map.get(null, null));
    }

    @Test
    public void testGetAfterRemove() {
        map.put("a", "b", "value4");
        map.removeMultiKey("a", "b");
        assertNull(map.get("a", "b"));
    }
}
