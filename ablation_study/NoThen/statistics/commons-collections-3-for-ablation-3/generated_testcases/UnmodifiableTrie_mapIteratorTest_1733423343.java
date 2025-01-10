
package org.apache.commons.collections4.trie;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.map.LinkedMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableTrie_mapIteratorTest {

    @Test
    public void testMapIterator() {
        // Given
        Trie<String, String> originalTrie = new LinkedMap<>();
        originalTrie.put("key1", "value1");
        Trie<String, String> unmodifiableTrie = UnmodifiableTrie.unmodifiableTrie(originalTrie);

        // When
        OrderedMapIterator<String, String> iterator = unmodifiableTrie.mapIterator();

        // Then
        assertTrue(iterator.hasNext());
        assertEquals("key1", iterator.next());
        assertEquals("value1", iterator.getValue());
    }
}
