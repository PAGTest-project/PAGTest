
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableMap_mapIteratorTest {

    private UnmodifiableMap<String, String> unmodifiableMap;
    private Map<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        unmodifiableMap = new UnmodifiableMap<>(map);
    }

    @Test
    public void testMapIteratorWithIterableMap() {
        IterableMap<String, String> iterableMap = new IterableMap<String, String>() {
            @Override
            public MapIterator<String, String> mapIterator() {
                return new EntrySetMapIterator<>(map);
            }

            @Override
            public String put(String key, String value) {
                return map.put(key, value);
            }

            @Override
            public String get(Object key) {
                return map.get(key);
            }

            @Override
            public Set<String> keySet() {
                return map.keySet();
            }

            @Override
            public Collection<String> values() {
                return map.values();
            }

            @Override
            public Set<Map.Entry<String, String>> entrySet() {
                return map.entrySet();
            }

            @Override
            public int size() {
                return map.size();
            }

            @Override
            public boolean isEmpty() {
                return map.isEmpty();
            }

            @Override
            public boolean containsKey(Object key) {
                return map.containsKey(key);
            }

            @Override
            public boolean containsValue(Object value) {
                return map.containsValue(value);
            }

            @Override
            public String remove(Object key) {
                return map.remove(key);
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {
                map.putAll(m);
            }

            @Override
            public void clear() {
                map.clear();
            }
        };

        unmodifiableMap = new UnmodifiableMap<>(iterableMap);
        MapIterator<String, String> iterator = unmodifiableMap.mapIterator();
        assertTrue(iterator instanceof UnmodifiableMapIterator);
    }

    @Test
    public void testMapIteratorWithoutIterableMap() {
        MapIterator<String, String> iterator = unmodifiableMap.mapIterator();
        assertTrue(iterator instanceof UnmodifiableMapIterator);
    }
}
