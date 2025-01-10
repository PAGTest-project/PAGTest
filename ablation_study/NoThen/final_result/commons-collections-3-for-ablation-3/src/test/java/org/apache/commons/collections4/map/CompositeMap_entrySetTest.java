
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_entrySetTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("1", "one");
        map1.put("2", "two");

        map2 = new HashMap<>();
        map2.put("3", "three");
        map2.put("4", "four");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testEntrySet() {
        Set<Map.Entry<String, String>> entrySet = compositeMap.entrySet();
        assertEquals(4, entrySet.size());
        assertTrue(entrySet.containsAll(map1.entrySet()));
        assertTrue(entrySet.containsAll(map2.entrySet()));
    }

    @Test
    public void testEntrySetAfterAddComposited() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("5", "five");
        compositeMap.addComposited(map3);

        Set<Map.Entry<String, String>> entrySet = compositeMap.entrySet();
        assertEquals(5, entrySet.size());
        assertTrue(entrySet.containsAll(map1.entrySet()));
        assertTrue(entrySet.containsAll(map2.entrySet()));
        assertTrue(entrySet.containsAll(map3.entrySet()));
    }

    @Test
    public void testEntrySetAfterRemoveComposited() {
        compositeMap.removeComposited(map2);

        Set<Map.Entry<String, String>> entrySet = compositeMap.entrySet();
        assertEquals(2, entrySet.size());
        assertTrue(entrySet.containsAll(map1.entrySet()));
        assertFalse(entrySet.containsAll(map2.entrySet()));
    }

    @Test
    public void testEntrySetAfterClear() {
        compositeMap.clear();

        Set<Map.Entry<String, String>> entrySet = compositeMap.entrySet();
        assertEquals(0, entrySet.size());
    }

    @Test
    public void testEntrySetAfterPut() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("6", "six");
        compositeMap.addComposited(newMap);

        Set<Map.Entry<String, String>> entrySet = compositeMap.entrySet();
        assertEquals(5, entrySet.size());
        assertTrue(entrySet.contains(new AbstractMap.SimpleEntry<>("6", "six")));
    }

    @Test
    public void testEntrySetAfterRemove() {
        compositeMap.remove("3");

        Set<Map.Entry<String, String>> entrySet = compositeMap.entrySet();
        assertEquals(3, entrySet.size());
        assertFalse(entrySet.contains(new AbstractMap.SimpleEntry<>("3", "three")));
    }
}
