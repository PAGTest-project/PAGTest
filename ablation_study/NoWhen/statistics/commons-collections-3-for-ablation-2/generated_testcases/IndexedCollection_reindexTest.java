
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexedCollection_reindexTest {

    private IndexedCollection<String, String> indexedCollection;
    private MultiMap<String, String> mockIndex;
    private Transformer<String, String> mockKeyTransformer;

    @BeforeEach
    void setUp() {
        Collection<String> mockCollection = new HashSet<>();
        mockCollection.add("element1");
        mockCollection.add("element2");

        mockIndex = MultiValueMap.multiValueMap(new HashMap<>());
        mockKeyTransformer = mock(Transformer.class);

        indexedCollection = new IndexedCollection<>(mockCollection, mockKeyTransformer, mockIndex, false);
    }

    @Test
    void testReindex() {
        // Given
        when(mockKeyTransformer.apply("element1")).thenReturn("key1");
        when(mockKeyTransformer.apply("element2")).thenReturn("key2");

        // When
        indexedCollection.reindex();

        // Then
        assertEquals("element1", indexedCollection.get("key1"));
        assertEquals("element2", indexedCollection.get("key2"));
        assertTrue(indexedCollection.contains("element1"));
        assertTrue(indexedCollection.contains("element2"));
    }
}
