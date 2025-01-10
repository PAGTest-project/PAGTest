
package org.apache.commons.collections4.bidimap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DualTreeBidiMap_previousKeyTest {

    private DualTreeBidiMap<Integer, String> bidiMap;

    @BeforeEach
    public void setUp() {
        bidiMap = new DualTreeBidiMap<>(new TreeMap<>(), new TreeMap<>());
        bidiMap.put(1, "one");
        bidiMap.put(2, "two");
        bidiMap.put(3, "three");
    }

    @Test
    public void testPreviousKey_EmptyMap() {
        bidiMap = new DualTreeBidiMap<>();
        assertNull(bidiMap.previousKey(1));
    }

    @Test
    public void testPreviousKey_KeyExists() {
        assertEquals(2, bidiMap.previousKey(3));
    }

    @Test
    public void testPreviousKey_KeyDoesNotExist() {
        assertNull(bidiMap.previousKey(4));
    }

    @Test
    public void testPreviousKey_FirstKey() {
        assertNull(bidiMap.previousKey(1));
    }

    @Test
    public void testPreviousKey_WithOrderedMap() {
        bidiMap = new DualTreeBidiMap<>(new TreeMap<>(), new TreeMap<>());
        bidiMap.put(1, "one");
        bidiMap.put(2, "two");
        bidiMap.put(3, "three");
        assertEquals(2, bidiMap.previousKey(3));
    }

    @Test
    public void testPreviousKey_WithHeadMap() {
        assertEquals(2, bidiMap.previousKey(3));
    }
}
