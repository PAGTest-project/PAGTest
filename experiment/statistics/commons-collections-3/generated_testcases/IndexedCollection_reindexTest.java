
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IndexedCollection_reindexTest {

    private IndexedCollection<String, String> indexedCollection;
    private MultiMap<String, String> mockIndex;
    private Collection<String> mockCollection;
    private Transformer<String, String> mockTransformer;

    @BeforeEach
    public void setUp() {
        mockCollection = new HashSet<>();
        mockIndex = MultiValueMap.multiValueMap(new HashMap<>());
        mockTransformer = mock(Transformer.class);
        indexedCollection = new IndexedCollection<>(mockCollection, mockTransformer, mockIndex, false);
    }

    @Test
    public void testReindex() {
        // Given
        mockCollection.add("element1");
        mockCollection.add("element2");
        when(mockTransformer.apply("element1")).thenReturn("key1");
        when(mockTransformer.apply("element2")).thenReturn("key2");

        // When
        indexedCollection.reindex();

        // Then
        assertEquals(2, mockIndex.size());
        verify(mockTransformer, times(2)).apply(anyString());
    }
}
