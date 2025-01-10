
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

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
    public void testPutSuccess() {
        String previousValue = compositeMap.put("1", "newOne");
        assertEquals("one", previousValue);
        assertEquals("newOne", compositeMap.get("1"));
    }

    @Test
    public void testPutNewKey() {
        String previousValue = compositeMap.put("5", "five");
        assertEquals(null, previousValue);
        assertEquals("five", compositeMap.get("5"));
    }

    @Test
    public void testPutWithoutMutator() {
        CompositeMap<String, String> mapWithoutMutator = new CompositeMap<>(mapOne, mapTwo);
        assertThrows(UnsupportedOperationException.class, () -> {
            mapWithoutMutator.put("1", "newOne");
        });
    }

    @Test
    public void testPutNullKey() {
        assertThrows(NullPointerException.class, () -> {
            compositeMap.put(null, "nullValue");
        });
    }

    @Test
    public void testPutNullValue() {
        String previousValue = compositeMap.put("1", null);
        assertEquals("one", previousValue);
        assertEquals(null, compositeMap.get("1"));
    }
}
