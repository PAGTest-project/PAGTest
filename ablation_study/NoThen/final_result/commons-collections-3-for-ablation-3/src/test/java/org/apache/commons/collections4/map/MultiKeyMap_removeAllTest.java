
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_removeAllTest {

    private static final Integer I1 = 1;
    private static final Integer I2 = 2;
    private static final Integer I3 = 3;
    private static final Integer I4 = 4;
    private static final Integer I5 = 5;

    private MultiKeyMap<Integer, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiKeyMap<>();
    }

    @Test
    public void testRemoveAllWithExistingKey() {
        map.put(I1, I2, "1-2");
        map.put(I1, I3, "1-3");
        map.put(I2, I3, "2-3");

        assertTrue(map.removeAll(I1));
        assertEquals(1, map.size());
        assertFalse(map.containsKey(I1, I2));
        assertFalse(map.containsKey(I1, I3));
        assertTrue(map.containsKey(I2, I3));
    }

    @Test
    public void testRemoveAllWithNonExistingKey() {
        map.put(I1, I2, "1-2");
        map.put(I1, I3, "1-3");

        assertFalse(map.removeAll(I4));
        assertEquals(2, map.size());
        assertTrue(map.containsKey(I1, I2));
        assertTrue(map.containsKey(I1, I3));
    }

    @Test
    public void testRemoveAllWithNullKey() {
        map.put(null, I2, "null-2");
        map.put(I1, I3, "1-3");

        assertTrue(map.removeAll(null));
        assertEquals(1, map.size());
        assertFalse(map.containsKey(null, I2));
        assertTrue(map.containsKey(I1, I3));
    }

    @Test
    public void testRemoveAllWithEmptyMap() {
        assertFalse(map.removeAll(I1));
        assertEquals(0, map.size());
    }

    @Test
    public void testRemoveAllWithMultipleKeys() {
        map.put(I1, I2, "1-2");
        map.put(I1, I3, "1-3");
        map.put(I2, I3, "2-3");
        map.put(I2, I4, "2-4");

        assertTrue(map.removeAll(I1));
        assertEquals(2, map.size());
        assertFalse(map.containsKey(I1, I2));
        assertFalse(map.containsKey(I1, I3));
        assertTrue(map.containsKey(I2, I3));
        assertTrue(map.containsKey(I2, I4));
    }
}
