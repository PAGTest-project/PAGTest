
package org.apache.commons.collections4.trie;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.map.LinkedMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableTrie_mapIteratorTest {

    @Test
    public void testMapIterator() {
        // Given
        Trie<String, String> trie = new LinkedMap<>();
        trie.put("key1", "value1");
        trie.put("key2", "value2");
        Trie<String, String> unmodifiableTrie = UnmodifiableTrie.unmodifiableTrie(trie);

        // When
        OrderedMapIterator<String, String> iterator = unmodifiableTrie.mapIterator();

        // Then
        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasPrevious());
    }
}
