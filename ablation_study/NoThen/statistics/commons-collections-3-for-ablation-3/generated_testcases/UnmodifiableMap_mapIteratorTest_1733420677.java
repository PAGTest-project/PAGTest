
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_mapIteratorTest {

    @Test
    public void testMapIteratorWithIterableMap() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        IterableMap<String, String> iterableMap = new IterableMap<String, String>() {
            @Override
            public MapIterator<String, String> mapIterator() {
                return new EntrySetMapIterator<>(map);
            }

            @Override
            public Set<Map.Entry<String, String>> entrySet() {
                return map.entrySet();
            }
        };
        UnmodifiableMap<String, String> unmodifiableMap = new UnmodifiableMap<>(iterableMap);

        // When
        MapIterator<String, String> iterator = unmodifiableMap.mapIterator();

        // Then
        assertTrue(iterator instanceof UnmodifiableMapIterator);
    }

    @Test
    public void testMapIteratorWithoutIterableMap() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        UnmodifiableMap<String, String> unmodifiableMap = new UnmodifiableMap<>(map);

        // When
        MapIterator<String, String> iterator = unmodifiableMap.mapIterator();

        // Then
        assertTrue(iterator instanceof UnmodifiableMapIterator);
    }
}
