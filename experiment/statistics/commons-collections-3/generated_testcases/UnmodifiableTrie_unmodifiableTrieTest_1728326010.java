
package org.apache.commons.collections4.trie;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableTrie_unmodifiableTrieTest {

    @Test
    void testUnmodifiableTrieWithUnmodifiableInstance() {
        // Given
        Trie<String, String> mockTrie = mock(Trie.class);
        when(mockTrie.getClass().isInstance(Unmodifiable.class)).thenReturn(true);

        // When
        Trie<String, String> result = UnmodifiableTrie.unmodifiableTrie(mockTrie);

        // Then
        assertSame(mockTrie, result);
    }

    @Test
    void testUnmodifiableTrieWithModifiableInstance() {
        // Given
        Trie<String, String> mockTrie = mock(Trie.class);
        when(mockTrie.getClass().isInstance(Unmodifiable.class)).thenReturn(false);

        // When
        Trie<String, String> result = UnmodifiableTrie.unmodifiableTrie(mockTrie);

        // Then
        assertTrue(result instanceof UnmodifiableTrie);
        assertNotSame(mockTrie, result);
    }
}
