
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
        Trie<String, String> trie = new UnmodifiableTrie<>(new LinkedMap<>());
        OrderedMapIterator<String, String> iterator = trie.mapIterator();

        assertNotNull(iterator);
        assertTrue(iterator instanceof UnmodifiableOrderedMapIterator);
    }
}
