
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_addCompositedTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;
    private Map<String, String> map3;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        map3 = new HashMap<>();
        map3.put("key5", "value5"); // No key collision

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testAddCompositedSuccess() {
        compositeMap.addComposited(map3);
        assertEquals(5, compositeMap.size());
    }

    @Test
    public void testAddCompositedKeyCollisionWithoutMutator() {
        Map<String, String> mapWithCollision = new HashMap<>();
        mapWithCollision.put("key2", "value5"); // Key collision with map1
        assertThrows(IllegalArgumentException.class, () -> {
            compositeMap.addComposited(mapWithCollision);
        });
    }

    @Test
    public void testAddCompositedKeyCollisionWithMutator() {
        compositeMap.setMutator(new CompositeMap.MapMutator<String, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
                // Handle collision
            }
        });

        Map<String, String> mapWithCollision = new HashMap<>();
        mapWithCollision.put("key2", "value5"); // Key collision with map1
        compositeMap.addComposited(mapWithCollision);
        assertEquals(5, compositeMap.size());
    }
}
