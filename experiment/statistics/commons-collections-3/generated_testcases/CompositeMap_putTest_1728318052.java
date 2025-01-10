
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        mapTwo = new HashMap<>();
        compositeMap = new CompositeMap<>(mapOne, mapTwo, new CompositeMap.MapMutator<String, String>() {
            private static final long serialVersionUID = 1L;

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
                throw new UnsupportedOperationException();
            }
        });
    }

    @Test
    public void testPutWithMutator() {
        String key = "key1";
        String value = "value1";
        String result = compositeMap.put(key, value);
        assertEquals(null, result);
        assertEquals(value, mapOne.get(key));
    }

    @Test
    public void testPutWithoutMutator() {
        compositeMap.setMutator(null);
        assertThrows(UnsupportedOperationException.class, () -> {
            compositeMap.put("key2", "value2");
        });
    }
}
