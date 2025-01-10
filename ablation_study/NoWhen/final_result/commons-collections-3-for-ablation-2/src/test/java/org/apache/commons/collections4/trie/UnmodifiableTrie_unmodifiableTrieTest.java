
package org.apache.commons.collections4.trie;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableTrie_unmodifiableTrieTest {

    @Test
    void testUnmodifiableTrieWithUnmodifiableTrie() {
        // Given
        Trie<String, String> mockTrie = mock(Trie.class);
        when(mockTrie.containsKey("key")).thenReturn(true);
        Trie<String, String> unmodifiableTrie = new UnmodifiableTrie<>(mockTrie);

        // When
        Trie<String, String> result = UnmodifiableTrie.unmodifiableTrie(unmodifiableTrie);

        // Then
        assertSame(unmodifiableTrie, result);
        assertTrue(result.containsKey("key"));
    }

    @Test
    void testUnmodifiableTrieWithModifiableTrie() {
        // Given
        Trie<String, String> mockTrie = mock(Trie.class);
        when(mockTrie.containsKey("key")).thenReturn(true);

        // When
        Trie<String, String> result = UnmodifiableTrie.unmodifiableTrie(mockTrie);

        // Then
        assertTrue(result instanceof UnmodifiableTrie);
        assertTrue(result.containsKey("key"));
    }
}
