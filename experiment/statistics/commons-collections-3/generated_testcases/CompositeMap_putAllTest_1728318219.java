
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_putAllTest {

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
    public void testPutAllWithMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        newMap.put("6", "six");

        compositeMap.setMutator(new CompositeMap.MapMutator<String, String>() {
            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> mapToAdd) {
                for (Map.Entry<? extends String, ? extends String> entry : mapToAdd.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }

            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                return map.put(key, value);
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
                // No-op for this test
            }
        });

        compositeMap.putAll(newMap);

        assertEquals("five", compositeMap.get("5"));
        assertEquals("six", compositeMap.get("6"));
    }

    @Test
    public void testPutAllWithoutMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        newMap.put("6", "six");

        assertThrows(UnsupportedOperationException.class, () -> {
            compositeMap.putAll(newMap);
        });
    }

    @Test
    public void testPutAllWithCollision() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("2", "newTwo");
        newMap.put("5", "five");

        compositeMap.setMutator(new CompositeMap.MapMutator<String, String>() {
            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> mapToAdd) {
                for (Map.Entry<? extends String, ? extends String> entry : mapToAdd.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }

            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                return map.put(key, value);
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
                // No-op for this test
            }
        });

        compositeMap.putAll(newMap);

        assertEquals("newTwo", compositeMap.get("2"));
        assertEquals("five", compositeMap.get("5"));
    }
}
