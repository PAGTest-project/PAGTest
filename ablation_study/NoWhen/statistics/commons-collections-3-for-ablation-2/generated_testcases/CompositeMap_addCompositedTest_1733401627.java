
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        map1.put("1", "one");
        map1.put("2", "two");

        map2 = new HashMap<>();
        map2.put("3", "three");
        map2.put("4", "four");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testAddCompositedSuccess() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        newMap.put("6", "six");

        compositeMap.addComposited(newMap);

        assertEquals(6, compositeMap.size());
        assertTrue(compositeMap.containsKey("5"));
        assertTrue(compositeMap.containsKey("6"));
    }

    @Test
    public void testAddCompositedKeyCollisionWithoutMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("1", "oneNew");
        newMap.put("6", "six");

        assertThrows(IllegalArgumentException.class, () -> {
            compositeMap.addComposited(newMap);
        });
    }

    @Test
    public void testAddCompositedKeyCollisionWithMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("1", "oneNew");
        newMap.put("6", "six");

        compositeMap.setMutator(new CompositeMap.MapMutator<String, String>() {
            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                return null;
            }

            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> mapToAdd) {
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
            }
        });

        compositeMap.addComposited(newMap);

        assertEquals(5, compositeMap.size());
        assertTrue(compositeMap.containsKey("1"));
        assertTrue(compositeMap.containsKey("6"));
    }
}
