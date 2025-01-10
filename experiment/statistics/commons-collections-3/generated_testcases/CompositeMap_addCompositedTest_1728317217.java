
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_addCompositedTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> mapOne;
    private Map<String, String> mapTwo;

    @BeforeEach
    public void setUp() {
        mapOne = new HashMap<>();
        mapOne.put("1", "one");
        mapOne.put("2", "two");

        mapTwo = new HashMap<>();
        mapTwo.put("3", "three");
        mapTwo.put("4", "four");

        compositeMap = new CompositeMap<>(mapOne, mapTwo);
    }

    @Test
    public void testAddCompositedNoCollision() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        newMap.put("6", "six");

        compositeMap.addComposited(newMap);

        assertEquals(6, compositeMap.size());
        assertTrue(compositeMap.containsKey("5"));
        assertTrue(compositeMap.containsKey("6"));
    }

    @Test
    public void testAddCompositedWithCollisionNoMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("1", "one_new");
        newMap.put("5", "five");

        assertThrows(IllegalArgumentException.class, () -> {
            compositeMap.addComposited(newMap);
        });
    }

    @Test
    public void testAddCompositedWithCollisionWithMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("1", "one_new");
        newMap.put("5", "five");

        CompositeMap.MapMutator<String, String> mutator = new CompositeMap.MapMutator<String, String>() {
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
        };

        compositeMap.setMutator(mutator);
        compositeMap.addComposited(newMap);

        assertEquals(5, compositeMap.size());
        assertTrue(compositeMap.containsKey("5"));
    }
}
