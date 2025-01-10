
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_mapIteratorTest {

    @Test
    public void testMapIteratorWithIterableMap() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        IterableMap<String, String> iterableMap = new IterableMap<String, String>() {
            @Override
            public MapIterator<String, String> mapIterator() {
                return new EntrySetMapIterator<>(originalMap);
            }

            @Override
            public String get(Object key) {
                return originalMap.get(key);
            }

            @Override
            public String put(String key, String value) {
                return originalMap.put(key, value);
            }

            @Override
            public String remove(Object key) {
                return originalMap.remove(key);
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {
                originalMap.putAll(m);
            }

            @Override
            public void clear() {
                originalMap.clear();
            }

            @Override
            public boolean containsKey(Object key) {
                return originalMap.containsKey(key);
            }

            @Override
            public boolean containsValue(Object value) {
                return originalMap.containsValue(value);
            }

            @Override
            public boolean isEmpty() {
                return originalMap.isEmpty();
            }

            @Override
            public int size() {
                return originalMap.size();
            }

            @Override
            public java.util.Set<String> keySet() {
                return originalMap.keySet();
            }

            @Override
            public java.util.Collection<String> values() {
                return originalMap.values();
            }

            @Override
            public java.util.Set<Entry<String, String>> entrySet() {
                return originalMap.entrySet();
            }
        };

        UnmodifiableMap<String, String> unmodifiableMap = new UnmodifiableMap<>(iterableMap);

        // When
        MapIterator<String, String> mapIterator = unmodifiableMap.mapIterator();

        // Then
        assertTrue(mapIterator instanceof UnmodifiableMapIterator);
    }

    @Test
    public void testMapIteratorWithoutIterableMap() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        UnmodifiableMap<String, String> unmodifiableMap = new UnmodifiableMap<>(originalMap);

        // When
        MapIterator<String, String> mapIterator = unmodifiableMap.mapIterator();

        // Then
        assertTrue(mapIterator instanceof UnmodifiableMapIterator);
    }
}
