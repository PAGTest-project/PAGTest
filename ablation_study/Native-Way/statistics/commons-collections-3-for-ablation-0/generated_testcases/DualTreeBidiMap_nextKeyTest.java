
package org.apache.commons.collections4.bidimap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DualTreeBidiMap_nextKeyTest {

    private DualTreeBidiMap<Integer, String> bidiMap;

    @BeforeEach
    public void setUp() {
        bidiMap = new DualTreeBidiMap<>(new TreeMap<>(), new TreeMap<>());
        bidiMap.put(1, "one");
        bidiMap.put(2, "two");
        bidiMap.put(3, "three");
    }

    @Test
    public void testNextKeyWithOrderedMap() {
        bidiMap = new DualTreeBidiMap<>(new TreeMap<>(), new TreeMap<>());
        bidiMap.put(1, "one");
        bidiMap.put(2, "two");
        bidiMap.put(3, "three");

        assertEquals(2, bidiMap.nextKey(1));
        assertEquals(3, bidiMap.nextKey(2));
        assertNull(bidiMap.nextKey(3));
    }

    @Test
    public void testNextKeyWithEmptyMap() {
        bidiMap = new DualTreeBidiMap<>(new TreeMap<>(), new TreeMap<>());
        assertNull(bidiMap.nextKey(1));
    }

    @Test
    public void testNextKeyWithNonExistentKey() {
        assertNull(bidiMap.nextKey(4));
    }

    @Test
    public void testNextKeyWithTailMap() {
        assertEquals(2, bidiMap.nextKey(1));
        assertEquals(3, bidiMap.nextKey(2));
        assertNull(bidiMap.nextKey(3));
    }

    @Test
    public void testNextKeyWithHeadMap() {
        bidiMap = new DualTreeBidiMap<>(new TreeMap<>(), new TreeMap<>());
        bidiMap.put(1, "one");
        bidiMap.put(2, "two");
        bidiMap.put(3, "three");

        assertEquals(2, bidiMap.nextKey(1));
        assertEquals(3, bidiMap.nextKey(2));
        assertNull(bidiMap.nextKey(3));
    }

    @Test
    public void testNextKeyWithSubMap() {
        bidiMap = new DualTreeBidiMap<>(new TreeMap<>(), new TreeMap<>());
        bidiMap.put(1, "one");
        bidiMap.put(2, "two");
        bidiMap.put(3, "three");

        assertEquals(2, bidiMap.nextKey(1));
        assertEquals(3, bidiMap.nextKey(2));
        assertNull(bidiMap.nextKey(3));
    }
}
