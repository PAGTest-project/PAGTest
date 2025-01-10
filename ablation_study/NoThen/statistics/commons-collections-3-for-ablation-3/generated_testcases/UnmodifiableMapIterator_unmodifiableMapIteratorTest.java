
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableMapIterator_unmodifiableMapIteratorTest {

    @Test
    void testUnmodifiableMapIteratorWithUnmodifiableIterator() {
        // Given
        MapIterator<String, String> mockIterator = new MockUnmodifiableMapIterator<>();

        // When
        MapIterator<String, String> result = UnmodifiableMapIterator.unmodifiableMapIterator(mockIterator);

        // Then
        assertSame(mockIterator, result);
    }

    @Test
    void testUnmodifiableMapIteratorWithModifiableIterator() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        MapIterator<String, String> modifiableIterator = new HashMapIterator<>(map);

        // When
        MapIterator<String, String> result = UnmodifiableMapIterator.unmodifiableMapIterator(modifiableIterator);

        // Then
        assertTrue(result instanceof UnmodifiableMapIterator);
    }

    @Test
    void testUnmodifiableMapIteratorWithNullIterator() {
        // Given
        MapIterator<String, String> nullIterator = null;

        // When / Then
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableMapIterator.unmodifiableMapIterator(nullIterator);
        });
    }

    // Mock implementation of UnmodifiableMapIterator for testing
    private static class MockUnmodifiableMapIterator<K, V> implements MapIterator<K, V>, Unmodifiable {
        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public K next() {
            return null;
        }

        @Override
        public void remove() {
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }

    // Mock implementation of MapIterator for testing
    private static class HashMapIterator<K, V> implements MapIterator<K, V> {
        private final Map<K, V> map;
        private java.util.Iterator<Map.Entry<K, V>> iterator;
        private Map.Entry<K, V> currentEntry;

        public HashMapIterator(Map<K, V> map) {
            this.map = map;
            this.iterator = map.entrySet().iterator();
        }

        @Override
        public K getKey() {
            return currentEntry.getKey();
        }

        @Override
        public V getValue() {
            return currentEntry.getValue();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            currentEntry = iterator.next();
            return currentEntry.getKey();
        }

        @Override
        public void remove() {
            iterator.remove();
        }

        @Override
        public V setValue(V value) {
            return currentEntry.setValue(value);
        }
    }
}
