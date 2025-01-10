
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_putTest {

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

        compositeMap = new CompositeMap<>(mapOne, mapTwo, new CompositeMap.MapMutator<String, String>() {
            @Override
            public String put(CompositeMap<String, String> map, Map<String, String>[] composited, String key, String value) {
                for (Map<String, String> m : composited) {
                    if (m.containsKey(key)) {
                        return m.put(key, value);
                    }
                }
                return composited[0].put(key, value);
            }

            @Override
            public void putAll(CompositeMap<String, String> map, Map<String, String>[] composited, Map<? extends String, ? extends String> mapToAdd) {
                for (Map.Entry<? extends String, ? extends String> entry : mapToAdd.entrySet()) {
                    put(map, composited, entry.getKey(), entry.getValue());
                }
            }

            @Override
            public void resolveCollision(CompositeMap<String, String> composite, Map<String, String> existing, Map<String, String> added, Collection<String> intersect) {
                // No-op for this test
            }
        });
    }

    @Test
    public void testPutWithMutator() {
        String oldValue = compositeMap.put("1", "newOne");
        assertEquals("one", oldValue);
        assertEquals("newOne", compositeMap.get("1"));
    }

    @Test
    public void testPutWithoutMutator() {
        compositeMap.setMutator(null);
        assertThrows(UnsupportedOperationException.class, () -> {
            compositeMap.put("1", "newOne");
        });
    }

    @Test
    public void testPutNewKey() {
        String oldValue = compositeMap.put("5", "five");
        assertNull(oldValue);
        assertEquals("five", compositeMap.get("5"));
    }

    @Test
    public void testPutExistingKeyInDifferentMap() {
        String oldValue = compositeMap.put("3", "newThree");
        assertEquals("three", oldValue);
        assertEquals("newThree", compositeMap.get("3"));
    }

    @Test
    public void testPutNullKey() {
        assertThrows(NullPointerException.class, () -> {
            compositeMap.put(null, "nullValue");
        });
    }

    @Test
    public void testPutNullValue() {
        String oldValue = compositeMap.put("1", null);
        assertEquals("one", oldValue);
        assertNull(compositeMap.get("1"));
    }

    @Test
    public void testPutAllWithMutator() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("1", "newOne");
        newMap.put("6", "six");
        compositeMap.putAll(newMap);
        assertEquals("newOne", compositeMap.get("1"));
        assertEquals("six", compositeMap.get("6"));
    }

    @Test
    public void testPutAllWithoutMutator() {
        compositeMap.setMutator(null);
        Map<String, String> newMap = new HashMap<>();
        newMap.put("1", "newOne");
        assertThrows(UnsupportedOperationException.class, () -> {
            compositeMap.putAll(newMap);
        });
    }

    @Test
    public void testRemove() {
        String removedValue = compositeMap.remove("1");
        assertEquals("one", removedValue);
        assertFalse(compositeMap.containsKey("1"));
    }

    @Test
    public void testRemoveNonExistentKey() {
        String removedValue = compositeMap.remove("5");
        assertNull(removedValue);
    }

    @Test
    public void testAddComposited() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        compositeMap.addComposited(newMap);
        assertTrue(compositeMap.containsKey("5"));
    }

    @Test
    public void testRemoveComposited() {
        Map<String, String> removedMap = compositeMap.removeComposited(mapOne);
        assertEquals(mapOne, removedMap);
        assertFalse(compositeMap.containsKey("1"));
    }

    @Test
    public void testClear() {
        compositeMap.clear();
        assertTrue(compositeMap.isEmpty());
    }
}
