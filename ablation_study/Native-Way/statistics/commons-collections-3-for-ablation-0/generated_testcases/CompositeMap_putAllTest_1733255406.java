
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        mapTwo = new HashMap<>();
        compositeMap = new CompositeMap<>(mapOne, mapTwo, new CompositeMap.MapMutator<String, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                return null;
            }

            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> mapToAdd) {
                for (Map.Entry<? extends String, ? extends String> entry : mapToAdd.entrySet()) {
                    mapOne.put(entry.getKey(), entry.getValue());
                }
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Test
    public void testPutAllWithMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key1", "value1");
        newMap.put("key2", "value2");

        compositeMap.putAll(newMap);

        assertTrue(mapOne.containsKey("key1"));
        assertTrue(mapOne.containsKey("key2"));
        assertEquals("value1", mapOne.get("key1"));
        assertEquals("value2", mapOne.get("key2"));
    }

    @Test
    public void testPutAllWithoutMutator() {
        compositeMap.setMutator(null);

        Map<String, String> newMap = new HashMap<>();
        newMap.put("key1", "value1");
        newMap.put("key2", "value2");

        assertThrows(UnsupportedOperationException.class, () -> {
            compositeMap.putAll(newMap);
        });
    }
}
