
package org.apache.commons.collections4.bidimap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnmodifiableOrderedBidiMap_keySetTest {

    private OrderedBidiMap<String, String> map;
    private UnmodifiableOrderedBidiMap<String, String> unmodifiableMap;

    @BeforeEach
    public void setUp() {
        map = new TreeBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        unmodifiableMap = UnmodifiableOrderedBidiMap.unmodifiableOrderedBidiMap(map);
    }

    @Test
    public void testKeySet() {
        Set<String> keySet = unmodifiableMap.keySet();
        assertEquals(2, keySet.size());
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
    }

    @Test
    public void testKeySetUnmodifiable() {
        Set<String> keySet = unmodifiableMap.keySet();
        assertThrows(UnsupportedOperationException.class, () -> keySet.add("key3"));
    }
}
