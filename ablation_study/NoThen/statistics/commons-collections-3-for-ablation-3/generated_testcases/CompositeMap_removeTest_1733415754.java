
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_removeTest {

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

        compositeMap = new CompositeMap<>(map1, map2, new CompositeMap.MapMutator<String, String>() {
            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
                // No-op
            }

            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                return map1.put(key, value);
            }

            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> t) {
                map1.putAll(t);
            }
        });
    }

    @Test
    public void testRemoveExistingKey() {
        assertTrue(compositeMap.containsKey("1"));
        assertEquals("one", compositeMap.remove("1"));
        assertFalse(compositeMap.containsKey("1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertFalse(compositeMap.containsKey("5"));
        assertNull(compositeMap.remove("5"));
    }

    @Test
    public void testRemoveFromSecondMap() {
        assertTrue(compositeMap.containsKey("3"));
        assertEquals("three", compositeMap.remove("3"));
        assertFalse(compositeMap.containsKey("3"));
    }

    @Test
    public void testRemoveAfterAddComposited() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("5", "five");
        compositeMap.addComposited(map3);

        assertTrue(compositeMap.containsKey("5"));
        assertEquals("five", compositeMap.remove("5"));
        assertFalse(compositeMap.containsKey("5"));
    }

    @Test
    public void testRemoveAfterPut() {
        compositeMap.put("6", "six");
        assertTrue(compositeMap.containsKey("6"));
        assertEquals("six", compositeMap.remove("6"));
        assertFalse(compositeMap.containsKey("6"));
    }
}
