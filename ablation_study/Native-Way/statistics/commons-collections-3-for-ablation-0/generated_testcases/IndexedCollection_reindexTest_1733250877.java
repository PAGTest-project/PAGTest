
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static org.mockito.Mockito.*;

public class IndexedCollection_reindexTest {

    private IndexedCollection<String, String> indexedCollection;
    private MultiMap<String, String> mockIndex;
    private Collection<String> mockCollection;
    private Transformer<String, String> mockTransformer;

    @BeforeEach
    public void setUp() {
        mockCollection = mock(Collection.class);
        mockIndex = mock(MultiMap.class);
        mockTransformer = mock(Transformer.class);
        indexedCollection = new IndexedCollection<>(mockCollection, mockTransformer, mockIndex, false);
    }

    @Test
    public void testReindex() {
        // Given
        String element1 = "element1";
        String element2 = "element2";
        Iterator<String> mockIterator = mock(Iterator.class);
        when(mockCollection.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true, true, false);
        when(mockIterator.next()).thenReturn(element1, element2);
        when(mockTransformer.apply(element1)).thenReturn("key1");
        when(mockTransformer.apply(element2)).thenReturn("key2");

        // When
        indexedCollection.reindex();

        // Then
        verify(mockIndex).clear();
        verify(mockIndex).put("key1", element1);
        verify(mockIndex).put("key2", element2);
    }
}
