
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_addCompositedTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2, new CompositeMap.MapMutator<String, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                return null;
            }

            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> t) {
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
            }
        });
    }

    @Test
    public void testAddCompositedNoCollision() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key5", "value5");
        newMap.put("key6", "value6");

        compositeMap.addComposited(newMap);

        assertEquals(6, compositeMap.size());
        assertTrue(compositeMap.containsKey("key5"));
        assertTrue(compositeMap.containsKey("key6"));
    }

    @Test
    public void testAddCompositedWithCollisionNoMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key1", "newValue1");
        newMap.put("key7", "value7");

        assertThrows(IllegalArgumentException.class, () -> compositeMap.addComposited(newMap));
    }

    @Test
    public void testAddCompositedWithCollisionWithMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key1", "newValue1");
        newMap.put("key7", "value7");

        compositeMap.setMutator(new CompositeMap.MapMutator<String, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                return null;
            }

            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> t) {
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
                // Handle collision
            }
        });

        compositeMap.addComposited(newMap);

        assertEquals(6, compositeMap.size());
        assertTrue(compositeMap.containsKey("key7"));
    }

    @Test
    public void testAddCompositedNullMap() {
        compositeMap.addComposited(null);

        assertEquals(4, compositeMap.size());
    }
}
