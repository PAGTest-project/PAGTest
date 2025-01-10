
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.keyvalue.MultiKey;

public class MultiKeyMap_removeMultiKeyTest {

    private MultiKeyMap<Integer, String> map;
    private static final Integer I1 = 1;
    private static final Integer I2 = 2;
    private static final Integer I3 = 3;

    @BeforeEach
    public void setUp() {
        map = new MultiKeyMap<>();
    }

    @Test
    public void testRemoveMultiKey_ExistingKey() {
        map.put(I1, I2, "1-2");
        assertEquals("1-2", map.removeMultiKey(I1, I2));
        assertFalse(map.containsKey(I1, I2));
    }

    @Test
    public void testRemoveMultiKey_NonExistingKey() {
        map.put(I1, I2, "1-2");
        assertNull(map.removeMultiKey(I3, I2));
        assertTrue(map.containsKey(I1, I2));
    }

    @Test
    public void testRemoveMultiKey_AfterPut() {
        map.put(I1, I2, "1-2");
        map.put(I1, I3, "1-3");
        assertEquals("1-2", map.removeMultiKey(I1, I2));
        assertTrue(map.containsKey(I1, I3));
        assertFalse(map.containsKey(I1, I2));
    }

    @Test
    public void testRemoveMultiKey_MultipleRemoves() {
        map.put(I1, I2, "1-2");
        map.put(I1, I3, "1-3");
        assertEquals("1-2", map.removeMultiKey(I1, I2));
        assertEquals("1-3", map.removeMultiKey(I1, I3));
        assertFalse(map.containsKey(I1, I2));
        assertFalse(map.containsKey(I1, I3));
    }

    @Test
    public void testRemoveMultiKey_NullKey() {
        assertThrows(NullPointerException.class, () -> map.removeMultiKey(null, I2));
        assertThrows(NullPointerException.class, () -> map.removeMultiKey(I1, null));
    }
}
